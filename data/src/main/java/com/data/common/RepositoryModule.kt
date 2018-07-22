package com.data.common

import android.content.Context
import com.data.R
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [(NetworkModule::class)])
class RepositoryModule {

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