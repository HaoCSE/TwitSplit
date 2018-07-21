package com.data.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(LanguagePack::class), (Country::class), (Configuration::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun languagePackDao(): LanguagePackDao
    abstract fun countryDao(): CountryDao
    abstract fun configurationDao(): ConfigurationDao
}