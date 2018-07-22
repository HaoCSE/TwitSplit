package net.fitken.twitsplit.home

import com.base.activity.BaseActivity
import net.fitken.twitsplit.R
import net.fitken.twitsplit.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeView {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

}
