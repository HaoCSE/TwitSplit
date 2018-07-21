package com.data.configuration

import com.data.persistence.Country
import com.data.persistence.LanguagePack
import com.data.model.response.ConfigurationResponse
import io.reactivex.Single

interface ConfigurationRepository {
    fun getConfiguration() : Single<ConfigurationResponse>
    fun getLanguagePack(languageCode: String): Single<LanguagePack>
    fun getCountries(): Single<List<Country>>
}