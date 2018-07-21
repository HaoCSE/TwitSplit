package com.data.common

import android.content.Context
import com.base.imageloader.ImageLoader
import com.base.injection.module.AppContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [(AppContextModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun getSharePreferences(context: Context): SharePreferenceManager {
        return SharePreferenceManager(context)
    }

    @Provides
    @Singleton
    fun getImageLoader(context: Context): ImageLoader {
        return ImageLoader(context)
    }
}