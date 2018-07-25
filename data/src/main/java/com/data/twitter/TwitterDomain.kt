package com.data.twitter

import com.data.model.TweetModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class TwitterDomain @Inject constructor(private var mRepository: TwitterRepository) {

    fun getHomeTimeline(page: Int): Single<List<TweetModel>> {
        return mRepository.getHomeTimeline(page).observeOn(AndroidSchedulers.mainThread())
    }

    fun postTweet(tweets: ArrayList<String>): Observable<Boolean> {
        return mRepository.postTweet(tweets).observeOn(AndroidSchedulers.mainThread())
    }
}