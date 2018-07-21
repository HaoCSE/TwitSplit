package com.data.common

import com.base.imageloader.ImageLoader
import com.data.configuration.ConfigurationRepository
import com.data.persistence.AppDatabase
import com.data.persistence.ConfigurationDao
import com.data.persistence.CountryDao
import com.data.persistence.LanguagePackDao
import com.data.user.UserRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(RepositoryModule::class), (AppModule::class)])
interface AppComponent {

    fun getSharePreferences(): SharePreferenceManager
    fun imageLoader(): ImageLoader
    fun languageChoosingRepository(): ConfigurationRepository
    fun userRepository(): UserRepository
    fun appDatabase(): AppDatabase
    fun languagePackDao(): LanguagePackDao
    fun countryDao(): CountryDao
    fun configurationDao(): ConfigurationDao
}