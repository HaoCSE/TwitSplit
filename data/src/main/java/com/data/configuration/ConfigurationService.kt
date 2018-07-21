package com.data.configuration

import com.data.model.response.ConfigurationResponse
import com.data.model.response.CountriesResponse
import com.data.model.response.LanguagePackResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ConfigurationService {

        @GET("/api/v1/language-packages")
    fun getLanguagePack(): Observable<LanguagePackResponse>

    @GET("/api/v1/countries")
    fun getCountries(): Single<CountriesResponse>

    @GET("/api/v1/configuration")
    fun getConfiguration(): Single<ConfigurationResponse>
}