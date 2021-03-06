package datn.datn_expansemanagement.screen.control_saving.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.PutInWalletSavingRequest
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

interface ControlSavingContract {
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterControl()
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getData(isCome: Boolean? = false, data : ItemAccountAccumulationViewModel? = null)
        abstract fun saveControlSaving(request: PutInWalletSavingRequest)
    }
}