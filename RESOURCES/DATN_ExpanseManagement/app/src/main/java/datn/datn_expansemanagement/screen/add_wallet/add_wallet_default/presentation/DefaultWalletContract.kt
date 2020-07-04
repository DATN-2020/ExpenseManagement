package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.WalletRequest

interface DefaultWalletContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun showListTypeWallet(list: MutableList<ViewModel>)
        fun handleCreateWallet()
        fun handleCreateWalletFail(message: String)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData()
        abstract fun getListTypeWallet()
        abstract fun createWallet(request: WalletRequest)
    }
}