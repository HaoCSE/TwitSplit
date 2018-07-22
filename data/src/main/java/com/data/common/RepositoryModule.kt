package com.data.common

import android.content.Context
import com.codepath.oauth.OAuthBaseClient
import com.data.R
import com.data.twitter.TwitterRepository
import com.data.twitter.TwitterRepositoryImpl
import com.data.twitter.TwitterRestClient
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

    @Provides
    @Singleton
    fun twitterClient(twitterRestClient: TwitterRestClient): TwitterRepository {
        return TwitterRepositoryImpl(twitterRestClient)
    }

    @Provides
    internal fun twitterClient(context: Context): TwitterRestClient {
        return OAuthBaseClient.getInstance(TwitterRestClient::class.java, context) as TwitterRestClient
    }
}