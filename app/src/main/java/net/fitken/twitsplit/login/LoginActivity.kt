package net.fitken.twitsplit.login

import android.os.Bundle
import com.base.utils.start
import com.data.twitter.TwitterRestClient
import net.fitken.twitsplit.R
import net.fitken.twitsplit.databinding.ActivityLoginBinding
import net.fitken.twitsplit.home.HomeActivity
import net.fitken.twitsplit.twitter.OAuthLoginActionBarActivity
import java.lang.Exception

class LoginActivity : OAuthLoginActionBarActivity<TwitterRestClient, ActivityLoginBinding, LoginViewModel>(), LoginView {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding.viewModel = mViewModel
    }

    override fun onLoginSuccess() {
        start<HomeActivity>()
    }

    override fun onLoginFailure(p0: Exception?) {
        p0?.printStackTrace()
    }

    override fun connectToTwitter() {
        client.connect()
    }
}
