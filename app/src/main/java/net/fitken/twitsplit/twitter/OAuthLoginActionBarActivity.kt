package net.fitken.twitsplit.twitter

import android.content.Intent
import android.databinding.ViewDataBinding
import com.base.activity.BaseActivity
import com.base.viewmodel.ActivityViewModel
import com.codepath.oauth.OAuthBaseClient
import com.codepath.utils.GenericsUtil

abstract class OAuthLoginActionBarActivity<T : OAuthBaseClient, B : ViewDataBinding, VM : ActivityViewModel>
    : BaseActivity<B, VM>(), OAuthBaseClient.OAuthAccessHandler {
    lateinit var client: T
        private set

    private val clientClass: Class<T>
        get() = GenericsUtil.getTypeArguments(OAuthLoginActionBarActivity::class.java, this.javaClass)[0] as Class<T>

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent
    }

    override fun onResume() {
        super.onResume()
        val clientClass = this.clientClass
        val uri = this.intent.data

        try {
            this.client = OAuthBaseClient.getInstance(clientClass, this) as T
            this.client.authorize(uri, this)
        } catch (var4: Exception) {
            var4.printStackTrace()
        }
    }
}
