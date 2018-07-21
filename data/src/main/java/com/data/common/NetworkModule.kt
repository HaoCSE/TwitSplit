package com.data.common

import com.base.injection.module.AppContextModule
import com.base.utils.debug.ShowLog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Modifier
import java.util.*
import java.util.concurrent.TimeUnit


@Module(includes = [(AppContextModule::class), (AppModule::class)])
class NetworkModule {

    companion object {
        private const val TIME_OUT = 10
        private const val KEY_CONTENT_TYPE = "Content-Type"
        const val KEY_AUTHORIZATION = "Authorization"
        const val VALUE_CONTENT_TYPE = "application/json"
    }

    @Provides
    fun httpLoggingInterceptor(debug: Boolean): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (debug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    fun okHttpClient(sharePreferenceManager: SharePreferenceManager,
                     interceptor: HttpLoggingInterceptor): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor)
        okBuilder.addInterceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()

            val headers = HashMap<String, String>()
            headers[KEY_CONTENT_TYPE] = VALUE_CONTENT_TYPE
            headers[KEY_AUTHORIZATION] =  sharePreferenceManager.userToken
            for ((key, value) in headers) {
                builder.addHeader(key, value)
                ShowLog.debug(value)
            }
            chain.proceed(builder.build())
        }

        okBuilder.connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

        return okBuilder.build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
        return gsonBuilder.create()
    }

    @Provides
    fun debug(): Boolean {
        return true
    }
}