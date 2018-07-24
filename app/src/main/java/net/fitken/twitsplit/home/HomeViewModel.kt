package net.fitken.twitsplit.home

import com.base.viewmodel.ActivityViewModel
import com.data.twitter.TwitterDomain

class HomeViewModel : ActivityViewModel() {

    private lateinit var mTwitterDomain: TwitterDomain
    private var mPage = 1
    private var mIsLoading: Boolean = false

    fun setViewModelAttributes(twitterDomain: TwitterDomain) {
        mTwitterDomain = twitterDomain
    }

    override fun onCreated() {
        super.onCreated()
        getHomeTimeline()
    }

    private fun getHomeTimeline() {
        val view: HomeView? = view()
        view?.let {
            addDisposable(mTwitterDomain.getHomeTimeline(mPage)
                    .doOnSubscribe {
                        if (!isLoadingMore()) {
                            view.showLoading()
                        }
                        mIsLoading = true
                    }
                    .doFinally {
                        view.hideLoading()
                        mIsLoading = false
                    }
                    .subscribe({
                        view.onTimelineLoaded(it)
                    }, {
                        view.showError(it)
                    }))
        }
    }

    fun refresh() {
        if (mIsLoading) {
            return
        }
        mPage = 1
        getHomeTimeline()
    }

    fun loadMore() {
        if (mIsLoading) {
            return
        }
        mPage++
        getHomeTimeline()
    }

    fun isLoadingMore(): Boolean {
        return mPage != 1
    }

    fun composeTweet() {
        val view: HomeView? = view()
        view?.onComposeTweet()
    }
}
