package net.fitken.twitsplit.home

import android.os.Bundle
import com.base.activity.BaseInjectingActivity
import com.data.model.TweetModel
import com.data.twitter.TwitterDomain
import net.fitken.twitsplit.R
import net.fitken.twitsplit.app.App
import net.fitken.twitsplit.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : BaseInjectingActivity<ActivityHomeBinding, HomeViewModel, HomeComponent>(), HomeView {

    @Inject
    lateinit var mAdapter: TweetAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun createComponent(): HomeComponent? {
        return DaggerHomeComponent.builder().appComponent(App.get(this).component())
                .homeModule(HomeModule()).build()
    }

    override fun onInject(component: HomeComponent) {
        component.inject(this)
    }

    @Inject
    fun setViewModelAttributes(twitterDomain: TwitterDomain) {
        mViewModel?.setViewModelAttributes(twitterDomain)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding.viewModel = mViewModel
        mViewDataBinding.mainRvTweets.adapter = mAdapter
    }

    override fun onTimelineLoaded(tweets: List<TweetModel>) {
        mAdapter.update(tweets)
    }
}
