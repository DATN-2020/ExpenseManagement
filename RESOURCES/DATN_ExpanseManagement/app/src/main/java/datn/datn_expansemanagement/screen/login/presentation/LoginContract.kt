package datn.datn_expansemanagement.screen.login.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.RegisterRequest
import datn.datn_expansemanagement.domain.response.RegisterResponse

interface LoginContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun handleAfterRegister(user: RegisterResponse)
        fun handleRegisterFail(ms: String)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun gotoMainActivity()
        abstract fun register(request: RegisterRequest)

    }
}