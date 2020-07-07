package datn.datn_expansemanagement.screen.account.item_account.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

interface ItemAccountContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterDeleteWallet()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData(tabId: Int, userId: Int)
        abstract fun gotoControlWallet(data: WalletViewModel, isOtherWallet: Boolean)
        abstract fun deleteWallet(walletId: Int)
    }
}