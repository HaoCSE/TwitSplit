package net.fitken.twitsplit.composetweet

import android.app.Activity
import android.os.Bundle
import com.base.activity.BaseInjectingActivity
import com.data.twitter.TwitterDomain
import net.fitken.twitsplit.R
import net.fitken.twitsplit.app.App
import net.fitken.twitsplit.databinding.ActivityComposeTweetBinding
import javax.inject.Inject

class ComposeTweetActivity : BaseInjectingActivity<ActivityComposeTweetBinding, ComposeTweetViewModel, ComposeTweetComponent>(), ComposeTweetView {

    override fun getLayoutId(): Int {
        return R.layout.activity_compose_tweet
    }

    override fun getViewModelClass(): Class<ComposeTweetViewModel> {
        return ComposeTweetViewModel::class.java
    }

    override fun createComponent(): ComposeTweetComponent? {
        return DaggerComposeTweetComponent.builder().appComponent(App.get(this).component())
                .composeTweetModule(ComposeTweetModule()).build()
    }

    override fun onInject(component: ComposeTweetComponent) {
        component.inject(this)
    }

    @Inject
    fun setViewModelAttributes(twitterDomain: TwitterDomain) {
        mViewModel?.setViewModelAttributes(twitterDomain)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding.viewModel = mViewModel
    }

    override fun onTweetPosted() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onClose() {
        onBackPressed()
    }
}
