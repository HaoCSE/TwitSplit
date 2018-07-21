package com.data.common

import android.arch.persistence.room.Room
import android.content.Context
import com.data.R
import com.data.configuration.ConfigurationRepository
import com.data.configuration.ConfigurationRepositoryImpl
import com.data.configuration.ConfigurationService
import com.data.persistence.AppDatabase
import com.data.persistence.ConfigurationDao
import com.data.persistence.CountryDao
import com.data.persistence.LanguagePackDao
import com.data.user.UserRepository
import com.data.user.UserRepositoryImpl
import com.data.user.UserService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [(NetworkModule::class)])
class RepositoryModule {

    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "survey.db")
                .build()
    }

    @Provides
    fun languagePackDataSource(appDatabase: AppDatabase): LanguagePackDao {
        return appDatabase.languagePackDao()
    }

    @Provides
    fun countryDataSource(appDatabase: AppDatabase): CountryDao {
        return appDatabase.countryDao()
    }

    @Provides
    fun configurationSource(appDatabase: AppDatabase): ConfigurationDao {
        return appDatabase.configurationDao()
    }

    @Provides
    @Singleton
    fun userService(userService: UserService, sharePreferenceManager: SharePreferenceManager): UserRepository {
        return UserRepositoryImpl(userService, sharePreferenceManager)
    }

    @Provides
    internal fun userService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun configurationService(configurationDao: ConfigurationDao, countryDao: CountryDao, languagePackDao: LanguagePackDao,
                             configurationService: ConfigurationService, sharePreferenceManager: SharePreferenceManager): ConfigurationRepository {
        return ConfigurationRepositoryImpl(configurationDao, countryDao, languagePackDao, configurationService, sharePreferenceManager)
    }

    @Provides
    internal fun configurationService(retrofit: Retrofit): ConfigurationService {
        return retrofit.create(ConfigurationService::class.java)
    }

    @Provides
    fun retrofit(context: Context, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        val builder = Retrofit.Builder()
                .baseUrl(baseUrl(context))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.build()
    }

    @Provides
    internal fun baseUrl(context: Context): String {
        return context.getString(R.string.server_domain)
    }
}