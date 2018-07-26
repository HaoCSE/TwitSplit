package net.fitken.twitsplit.composetweet

import android.databinding.ObservableField
import com.base.exception.ManuallyException
import com.base.utils.debug.ShowLog
import com.base.viewmodel.ActivityViewModel
import com.data.twitter.TwitterDomain

class ComposeTweetViewModel : ActivityViewModel() {
    companion object {
        const val MAX_MESSAGE_LENGTH = 50
    }
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

    fun splitMessage(message: String): ArrayList<String> {
        if (message.length > MAX_MESSAGE_LENGTH) { //if the message length more than Twitter max length then split
            var numOfMessage: Int = Math.ceil((message.length.toDouble() / MAX_MESSAGE_LENGTH.toDouble())).toInt() //calculate the number of message need to split

            val arrMessages: ArrayList<String> = ArrayList()

            var startIndex = 0
            var endIndex: Int
            var index = 1
            var additionLength = 0
            while (index <= numOfMessage) {
                var prefix = "$index/$numOfMessage"

                //re-calculate the number of message need to split because we split message by the last index of space
                numOfMessage = Math.ceil((message.length.toDouble() + numOfMessage * prefix.length + 1 + additionLength) / MAX_MESSAGE_LENGTH.toDouble()).toInt()

                prefix = "$index/$numOfMessage"
                endIndex = startIndex + (MAX_MESSAGE_LENGTH - prefix.length)
                if (endIndex > message.length) {
                    endIndex = message.length
                }

                val msg = message.substring(startIndex, endIndex)
                //get last index of space because we don't want the split sentence have a no meaning word
                var lastSpaceIndex = startIndex + msg.lastIndexOf(" ")
                if (index == numOfMessage) {
                    lastSpaceIndex = endIndex
                }
                additionLength = endIndex - lastSpaceIndex
                arrMessages.add("$prefix ${message.substring(startIndex, lastSpaceIndex).trim()}")
                startIndex = lastSpaceIndex
                index++
            }
            return arrMessages
        }
        return arrayListOf(message)
    }
}
