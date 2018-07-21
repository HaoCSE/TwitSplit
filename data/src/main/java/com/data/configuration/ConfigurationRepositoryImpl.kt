package com.data.configuration

import com.data.common.SharePreferenceManager
import com.data.persistence.*
import com.data.model.response.ConfigurationResponse
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ConfigurationRepositoryImpl(private var mConfigurationDao: ConfigurationDao, private var mCountryDao: CountryDao,
                                  private var mLanguagePackDao: LanguagePackDao, private var mService: ConfigurationService,
                                  private var mSharePreferenceManager: SharePreferenceManager)
    : ConfigurationRepository {

    override fun getConfiguration(): Single<ConfigurationResponse> {
        return mService.getConfiguration()
                .doOnSuccess {
                    val configurationResponse = it
                    mConfigurationDao.getConfiguration()
                            .subscribeOn(Schedulers.io())
                            .doFinally {
                                //insert data to database
                                mConfigurationDao.insertConfiguration(configurationResponse.data)
                            }
                            .subscribe({
                                //database already have data
                                if (configurationResponse.data.languagePackVersion != it.languagePackVersion) {
                                    //but language pack is out date
                                    getLanguagePackToPersistence()
                                }
                            }, {
                                //database have no data yet
                                getLanguagePackToPersistence()
                            })
                }
                .subscribeOn(Schedulers.io())
    }

    private fun getLanguagePackToPersistence() {
        mService.getLanguagePack()
                .doOnNext {
                    Observable.fromIterable(it.data)
                            .doOnNext {
                                //persistence data to database
                                val languagePack = LanguagePack(it.code, it.name, Gson().toJsonTree(it.dict).asJsonObject.toString())
                                mLanguagePackDao.insertLanguagePack(languagePack)
                            }.subscribe()
                }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun getLanguagePack(languageCode: String): Single<LanguagePack> {
        return mLanguagePackDao.getLanguageByLanguageCode(languageCode).subscribeOn(Schedulers.io())
    }

    override fun getCountries(): Single<List<Country>> {
        return mCountryDao.getCountries()
                .flatMap {
                    if (it.isEmpty()) {
                        //get from API
                        mService.getCountries().map {
                            //insert to database
                            mCountryDao.insertCountries(it.data)
                            it.data
                        }
                    } else {
                        Single.just(it)
                    }
                }
                .filter { it.isNotEmpty() }
                .toSingle()
                .subscribeOn(Schedulers.io())
    }
}