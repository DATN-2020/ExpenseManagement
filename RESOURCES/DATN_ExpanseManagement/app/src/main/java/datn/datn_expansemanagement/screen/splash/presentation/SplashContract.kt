package datn.datn_expansemanagement.screen.splash.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent

interface SplashContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData()
        abstract fun gotoLoginActivity(isLogin: Boolean, user: PassportDataIntent? = null)
    }
}