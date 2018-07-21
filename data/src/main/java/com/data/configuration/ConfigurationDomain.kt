package com.data.configuration

import com.data.persistence.Country
import com.data.persistence.LanguagePack
import com.data.model.response.ConfigurationResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ConfigurationDomain @Inject constructor(private var mRepository: ConfigurationRepository) {

    fun getConfiguration(): Single<ConfigurationResponse> {
        return mRepository.getConfiguration().observeOn(AndroidSchedulers.mainThread())
    }

    fun getLanguagePack(languageCode: String): Single<LanguagePack> {
        return mRepository.getLanguagePack(languageCode).observeOn(AndroidSchedulers.mainThread())
    }

    fun getCountries(): Single<List<Country>> {
        return mRepository.getCountries().observeOn(AndroidSchedulers.mainThread())
    }
}