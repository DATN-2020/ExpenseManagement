package datn.datn_expansemanagement.screen.login

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.login.presentation.LoginView

class LoginActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val isLogin = intent?.getBooleanExtra("isLogin", true)
        return LoginView(this, LoginView.ViewCreator(this, null), isLogin)
    }

}