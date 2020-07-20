package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface SavingWalletContact {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun showListBank(list: MutableList<ViewModel>)
        fun handleAfterCreate()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData()
        abstract fun getListBank()
        abstract fun createWalletSaving()
    }
}