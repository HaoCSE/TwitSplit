package net.fitken.twitsplit.login

import android.os.Bundle
import com.base.utils.start
import net.fitken.twitsplit.MainActivity
import net.fitken.twitsplit.R
import net.fitken.twitsplit.databinding.ActivityLoginBinding
import net.fitken.twitsplit.twitterclient.OAuthLoginActionBarActivity
import net.fitken.twitsplit.twitterclient.RestClient
import java.lang.Exception

class LoginActivity : OAuthLoginActionBarActivity<RestClient, ActivityLoginBinding, LoginViewModel>(), LoginView {

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
        start<MainActivity>()
    }

    override fun onLoginFailure(p0: Exception?) {
        p0?.printStackTrace()
    }

    override fun connectToTwitter() {
        client.connect()
    }
}
