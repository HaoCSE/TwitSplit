package net.fitken.twitsplit.home

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.imageloader.ImageLoader
import com.base.recyclerview.RecyclerAdapter
import com.data.model.TweetModel
import net.fitken.twitsplit.R
import net.fitken.twitsplit.databinding.ItemTweetBinding

class TweetAdapter(private val mImageLoader: ImageLoader) : RecyclerAdapter<TweetAdapter.TweetViewHolder, TweetModel>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder? {
        return TweetViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tweet, parent,
                false), mImageLoader)
    }

    class TweetViewHolder(itemTweetBinding: ItemTweetBinding, private val mImageLoader: ImageLoader) : RecyclerAdapter.BaseHolder<ItemTweetBinding,
            TweetModel>(itemTweetBinding) {
        override fun bindData(data: TweetModel) {
            super.bindData(data)
            mViewDataBinding.tweet = data

            var userAvatarUrl = data.user!!.profileImgUrl!!
            if (userAvatarUrl.contains("_normal")) {
                userAvatarUrl = userAvatarUrl.replace("_normal", "")
            }
            mImageLoader.load(userAvatarUrl).into(mViewDataBinding.ivAvatar)
            if (data.entities!!.media == null || data.entities!!.media!!.isEmpty()) {
                mViewDataBinding.ivContent.visibility = View.GONE
                return
            }else {
                mViewDataBinding.ivContent.visibility = View.VISIBLE
            }
            val imgUrl: String
            val listMedia = data.entities!!.media
            imgUrl = listMedia!![0].mediaUrl!!
            mImageLoader.load(imgUrl)
                    .placeHolderId(R.drawable.placeholder)
                    .errorHolderId(R.drawable.image_not_found)
                    .into(mViewDataBinding.ivContent)
        }
    }
}