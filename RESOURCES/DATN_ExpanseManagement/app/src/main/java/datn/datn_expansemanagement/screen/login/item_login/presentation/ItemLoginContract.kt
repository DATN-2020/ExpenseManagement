package datn.datn_expansemanagement.screen.login.item_login.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.PassportRequest

interface ItemLoginContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun handleAfterLogin()
        fun handleLoginFail(message: String)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun logInApp(passportRequest: PassportRequest)
    }
}