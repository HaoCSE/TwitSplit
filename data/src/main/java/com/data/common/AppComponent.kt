package com.data.common

import com.base.imageloader.ImageLoader
import com.data.twitter.TwitterRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(RepositoryModule::class), (AppModule::class)])
interface AppComponent {

    fun getSharePreferences(): SharePreferenceManager
    fun imageLoader(): ImageLoader
    fun getTwitterRepository(): TwitterRepository
}