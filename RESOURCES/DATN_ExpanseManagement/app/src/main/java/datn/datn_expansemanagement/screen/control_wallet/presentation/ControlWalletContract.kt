package datn.datn_expansemanagement.screen.control_wallet.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.TransferRequest
import datn.datn_expansemanagement.domain.request.UpdateWalletRequest
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

interface ControlWalletContract {
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterUpdate()
        fun showListWallet(list: MutableList<ViewModel>)
        fun handleTransferWallet()
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getListWallet(userId: Int, idWallet: Int)
        abstract fun getData(data: WalletViewModel, isOtherWallet: Boolean?)
        abstract fun updateWallet(idWallet: Int, request: UpdateWalletRequest)
        abstract fun transferWallet(transferRequest: TransferRequest)
    }
}