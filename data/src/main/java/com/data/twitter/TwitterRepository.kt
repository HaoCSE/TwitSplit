package com.data.twitter

import com.data.model.TweetModel
import io.reactivex.Observable
import io.reactivex.Single

interface TwitterRepository {
    fun getHomeTimeline(page: Int): Single<List<TweetModel>>
    fun postTweet(tweets: ArrayList<String>): Observable<Boolean>
}