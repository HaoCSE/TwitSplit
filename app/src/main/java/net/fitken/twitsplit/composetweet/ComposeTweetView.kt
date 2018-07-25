package net.fitken.twitsplit.composetweet

import com.base.viewmodel.CommonView

interface ComposeTweetView : CommonView {
    fun onClose()
    fun onTweetPosted()
}
