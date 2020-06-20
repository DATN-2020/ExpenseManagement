package datn.datn_expansemanagement.screen.splash.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.splash.domain.SplashMapper

class SplashPresenter(private val screenNavigator: AndroidScreenNavigator) :
    SplashContract.Presenter() {
    private val mResource = SplashResource()
    override fun getData() {
        view?.showData(SplashMapper(mResource).map(""))
    }

    override fun gotoLoginActivity(isLogin: Boolean) {
        screenNavigator.gotoLoginActivity(isLogin)
    }
}