package net.fitken.twitsplit.composetweet

import android.databinding.ObservableField
import com.base.exception.ManuallyException
import com.base.utils.debug.ShowLog
import com.base.viewmodel.ActivityViewModel
import com.data.twitter.TwitterDomain
import net.fitken.twitsplit.app.MAX_MESSAGE_LENGTH
import net.fitken.twitsplit.app.splitMessage

class ComposeTweetViewModel : ActivityViewModel() {
    private lateinit var mTwitterDomain: TwitterDomain

    var mTweet = ObservableField<String>("")

    fun setViewModelAttributes(twitterDomain: TwitterDomain) {
        mTwitterDomain = twitterDomain
    }

    fun close() {
        val view: ComposeTweetView? = view()
        view?.onClose()
    }

    fun postTweet() {
        val view: ComposeTweetView? = view()
        view?.let {
            if (mTweet.get()!!.isBlank()) {
                return
            }
            if (mTweet.get()!!.length > MAX_MESSAGE_LENGTH && mTweet.get()!!.lastIndexOf(" ") == -1) {
                view.showError(ManuallyException("Your tweet is invalid!"))
                return
            }
            val tweets = splitMessage(mTweet.get()!!)
            ShowLog.error(tweets)
            addDisposable(mTwitterDomain.postTweet(tweets)
                    .doOnSubscribe { view.showLoading() }
                    .doFinally { view.hideLoading() }
                    .subscribe({}, {
                        view.showError(it)
                    }, {
                        view.onTweetPosted()
                    }))
        }
    }
}
