package datn.datn_expansemanagement.screen.splash.item.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface ItemSplashContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : MvpPresenter<View>()
}