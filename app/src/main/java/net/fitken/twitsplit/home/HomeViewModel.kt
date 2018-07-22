package net.fitken.twitsplit.home

import com.base.viewmodel.ActivityViewModel
import com.data.twitter.TwitterDomain

class HomeViewModel : ActivityViewModel() {

    private lateinit var mTwitterDomain: TwitterDomain
    private var mPage = 1

    fun setViewModelAttributes(twitterDomain: TwitterDomain) {
        mTwitterDomain = twitterDomain
    }

    override fun onCreated() {
        super.onCreated()
        getHomeTimeline()
    }

    fun getHomeTimeline() {
        val view: HomeView? = view()
        view?.let {
            addDisposable(mTwitterDomain.getHomeTimeline(mPage)
                    .doOnSubscribe { view.showLoading() }
                    .doFinally { view.hideLoading() }
                    .subscribe({
                        view.onTimelineLoaded(it)
                    }, {
                        view.showError(it)
                    }))
        }
    }
}
