package com.data.twitter

import android.os.Handler
import android.os.Looper
import com.data.model.TweetModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject


class TwitterRepositoryImpl(private val mTwitterRestClient: TwitterRestClient) : TwitterRepository {

    override fun getHomeTimeline(page: Int): Single<List<TweetModel>> {
        return Single.create(
                SingleOnSubscribe<List<TweetModel>> { it ->
                    val mainHandler = Handler(Looper.getMainLooper())
                    val myRunnable = Runnable {
                        mTwitterRestClient.getHomeTimeline(page, object : JsonHttpResponseHandler() {
                            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONArray?) {
                                val gson = Gson()
                                val listType = object : TypeToken<List<TweetModel>>() {

                                }.type
                                it.onSuccess(gson.fromJson(response.toString(), listType))
                            }


                            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                                super.onFailure(statusCode, headers, throwable, errorResponse)
                                if (throwable != null) {
                                    it.onError(throwable)
                                } else {
                                    it.onError(Throwable())
                                }
                            }
                        })
                    }
                    mainHandler.post(myRunnable)
                }).subscribeOn(Schedulers.io())

    }

    override fun postTweet(tweet: String): Single<Boolean> {
        return Single.create(
                SingleOnSubscribe<Boolean> { it ->
                    val mainHandler = Handler(Looper.getMainLooper())
                    val mRunnable = Runnable {
                        mTwitterRestClient.postTweet(tweet, object : JsonHttpResponseHandler() {

                            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                                super.onSuccess(statusCode, headers, response)
                                it.onSuccess(true)
                            }
                            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONArray?) {
                                super.onSuccess(statusCode, headers, response)
                                it.onSuccess(true)
                            }

                            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                                super.onFailure(statusCode, headers, throwable, errorResponse)
                                if (throwable != null) {
                                    it.onError(throwable)
                                } else {
                                    it.onError(Throwable())
                                }
                            }
                        })
                    }
                    mainHandler.post(mRunnable)
                }
        ).subscribeOn(Schedulers.io())
    }
}