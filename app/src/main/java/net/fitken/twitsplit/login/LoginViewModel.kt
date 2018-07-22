package net.fitken.twitsplit.login

import com.base.viewmodel.ActivityViewModel

class LoginViewModel : ActivityViewModel() {

    fun login() {
        val view: LoginView? = view()
        view?.connectToTwitter()
    }
}
