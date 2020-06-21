package datn.datn_expansemanagement.screen.login.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface LoginContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun gotoMainActivity()
    }
}