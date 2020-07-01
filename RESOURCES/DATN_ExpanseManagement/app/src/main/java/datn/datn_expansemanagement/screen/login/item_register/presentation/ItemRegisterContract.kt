package datn.datn_expansemanagement.screen.login.item_register.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.RegisterRequest

interface ItemRegisterContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun handleAfterRegister(userName: String)
        fun handleRegisterFail(ms: String)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun register(request: RegisterRequest)
    }
}