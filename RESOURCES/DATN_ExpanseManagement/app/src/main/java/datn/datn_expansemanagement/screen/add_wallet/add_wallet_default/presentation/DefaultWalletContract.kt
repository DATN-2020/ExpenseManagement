package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface DefaultWalletContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData()
    }
}