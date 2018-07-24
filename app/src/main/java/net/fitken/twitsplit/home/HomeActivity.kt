package net.fitken.twitsplit.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.base.activity.BaseInjectingActivity
import com.base.utils.start
import com.base.utils.startForResult
import com.data.model.TweetModel
import com.data.twitter.TwitterDomain
import net.fitken.twitsplit.R
import net.fitken.twitsplit.app.App
import net.fitken.twitsplit.composetweet.ComposeTweetActivity
import net.fitken.twitsplit.databinding.ActivityHomeBinding
import javax.inject.Inject


class HomeActivity : BaseInjectingActivity<ActivityHomeBinding, HomeViewModel, HomeComponent>(), HomeView {

    companion object {
        const val COMPOSE_TWEET_REQUEST_CODE = 1000
    }

    @Inject
    lateinit var mAdapter: TweetAdapter
    private lateinit var mLayoutManager: LinearLayoutManager


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

        initUI()
    }

    private fun initUI() {
        // Find the view_toolbar view inside the activity layout
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the view_toolbar exists in the activity and is not null
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setLogo(R.mipmap.ic_launcher)
        supportActionBar!!.setDisplayUseLogoEnabled(true)

        mViewDataBinding.mainSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        mViewDataBinding.mainSwipeContainer.setOnRefreshListener {
            mViewModel?.refresh()
        }

        mViewDataBinding.mainRvTweets.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mLayoutManager = mViewDataBinding.mainRvTweets.layoutManager as LinearLayoutManager
        mViewDataBinding.mainRvTweets.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mAdapter.itemCount - 1) {
                    mViewModel?.loadMore()
                }
            }
        })
    }

    override fun onTimelineLoaded(tweets: List<TweetModel>) {
        mViewDataBinding.mainSwipeContainer.isRefreshing = false
        if (mViewModel?.isLoadingMore()!!) {
            mAdapter.appenItems(tweets)
        } else {
            mAdapter.update(tweets)
        }
    }

    override fun showError(throwable: Throwable) {
        super.showError(throwable)
        mViewDataBinding.mainSwipeContainer.isRefreshing = false
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onComposeTweet() {
        startForResult<ComposeTweetActivity>(COMPOSE_TWEET_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COMPOSE_TWEET_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mViewModel?.refresh()
        }
    }
}
