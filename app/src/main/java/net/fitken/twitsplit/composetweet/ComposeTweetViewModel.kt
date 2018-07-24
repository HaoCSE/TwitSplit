package net.fitken.twitsplit.composetweet

import android.databinding.ObservableField
import com.base.utils.debug.ShowLog
import com.base.viewmodel.ActivityViewModel
import com.data.twitter.TwitterDomain
import net.fitken.twitsplit.app.splitMessage

class ComposeTweetViewModel : ActivityViewModel() {
    private lateinit var mTwitterDomain: TwitterDomain

    var mTweet = ObservableField<String>("")

    fun setViewModelAttributes(twitterDomain: TwitterDomain) {
        mTwitterDomain = twitterDomain
    }

    fun postTweet() {
        var split = splitMessage("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.")
        for (item in split) {
            ShowLog.error(item)
        }
        ShowLog.error(split)
        ShowLog.error(splitMessage("I can't believe Tweeter now supports"))
    }

//    fun postTweet() {
//        val view: ComposeTweetView? = view()
//        view?.let {
//            if (mTweet.get()!!.isBlank()) {
//                return
//            }
//            addDisposable(mTwitterDomain.postTweet("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself")
//                    .doOnSubscribe { view.showLoading() }
//                    .doFinally { view.hideLoading() }
//                    .subscribe({
//                        view.onTweetPosted()
//                    },{
//                        view.showError(it)
//                    }))
//        }
//    }
}
