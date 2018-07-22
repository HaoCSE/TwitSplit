package net.fitken.twitsplit.home

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.base.recyclerview.RecyclerAdapter
import com.data.model.TweetModel
import net.fitken.twitsplit.R
import net.fitken.twitsplit.databinding.ItemTweetBinding

class TweetAdapter : RecyclerAdapter<TweetAdapter.TweetViewHolder, TweetModel>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder? {
        return TweetViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tweet, parent, false))
    }

    class TweetViewHolder(itemTweetBinding: ItemTweetBinding) : RecyclerAdapter.BaseHolder<ItemTweetBinding,
            TweetModel>(itemTweetBinding) {
        override fun bindData(data: TweetModel) {
            super.bindData(data)
            mViewDataBinding.tweet = data
        }
    }
}