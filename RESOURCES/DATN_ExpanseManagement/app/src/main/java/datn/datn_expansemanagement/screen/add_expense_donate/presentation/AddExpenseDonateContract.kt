package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface AddExpenseDonateContract {
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun showBottomData(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getData(isDonate: Boolean = false)
        abstract fun gotoCategoryActivity(categoryId: Int? = null, isDonate: Boolean = false)
        abstract fun gotoChooseWalletActivity(walletId: Int? = null)
        abstract fun gotoChooseTripActivity()
        abstract fun gotoChooseFriend()
        abstract fun gotoLocationActivity()
        abstract fun getListBudget(idWallet: Int)
    }
}