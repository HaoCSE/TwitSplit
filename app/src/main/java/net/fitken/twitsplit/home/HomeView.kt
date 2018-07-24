package net.fitken.twitsplit.home

import com.base.viewmodel.CommonView
import com.data.model.TweetModel

interface HomeView : CommonView {
    fun onTimelineLoaded(tweets: List<TweetModel>)
    fun onComposeTweet()
}
