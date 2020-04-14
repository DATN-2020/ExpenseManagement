package datn.datn_expansemanagement.screen.overview.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface OverviewContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData()
    }
}