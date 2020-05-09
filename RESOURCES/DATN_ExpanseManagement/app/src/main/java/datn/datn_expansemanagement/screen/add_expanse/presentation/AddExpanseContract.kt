package datn.datn_expansemanagement.screen.add_expanse.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface AddExpanseContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : MvpPresenter<View>(){

    }
}