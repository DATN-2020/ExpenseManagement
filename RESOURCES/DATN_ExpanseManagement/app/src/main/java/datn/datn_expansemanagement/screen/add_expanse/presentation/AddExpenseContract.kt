package datn.datn_expansemanagement.screen.add_expanse.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.InOutComeRequest

interface AddExpenseContract {
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterCreate()
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getData()
        abstract fun gotoHistoryActivity()
        abstract fun createExpense(inOutComeRequest: InOutComeRequest)
    }
}