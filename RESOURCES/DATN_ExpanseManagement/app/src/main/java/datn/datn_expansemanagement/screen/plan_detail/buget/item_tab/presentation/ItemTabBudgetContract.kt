package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.InOutComeRequest
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel

interface ItemTabBudgetContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterPayBill()
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData(tab: TabItemViewModel, idWallet: Int)
        abstract fun payBill(request: InOutComeRequest)
        abstract fun gotoReportDetail(data: ReportViewModel)
        abstract fun deleteBudget(idBudget: Int)

    }
}