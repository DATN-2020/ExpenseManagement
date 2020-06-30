package datn.datn_expansemanagement.screen.login.item_create_wallet.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.WalletRequest

interface ItemCreateWalletContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun handleCreateWallet()
        fun handleCreateWalletFail(message: String)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun createWallet(walletRequest: WalletRequest)
    }
}