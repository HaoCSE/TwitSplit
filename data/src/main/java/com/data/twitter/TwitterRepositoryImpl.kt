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

                            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONArray?) {
                                if (throwable != null) {
                                    it.onError(throwable)
                                } else {
                                    it.onError(Throwable())
                                }
                                super.onFailure(statusCode, headers, throwable, errorResponse)
                            }
                        })
                    }
                    mainHandler.post(myRunnable)
                }).subscribeOn(Schedulers.io())

    }
}