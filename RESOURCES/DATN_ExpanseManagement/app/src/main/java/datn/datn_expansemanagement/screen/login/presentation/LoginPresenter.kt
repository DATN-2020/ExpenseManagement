package datn.datn_expansemanagement.screen.login.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator

class LoginPresenter(private val screenNavigator: AndroidScreenNavigator) : LoginContract.Presenter() {
    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }
}