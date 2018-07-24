package com.data.twitter

import com.data.model.TweetModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class TwitterDomain @Inject constructor(private var mRepository: TwitterRepository) {

    fun getHomeTimeline(page: Int): Single<List<TweetModel>> {
        return mRepository.getHomeTimeline(page).observeOn(AndroidSchedulers.mainThread())
    }

    fun postTweet(tweet: String): Single<Boolean> {
        return mRepository.postTweet(tweet).observeOn(AndroidSchedulers.mainThread())
    }
}