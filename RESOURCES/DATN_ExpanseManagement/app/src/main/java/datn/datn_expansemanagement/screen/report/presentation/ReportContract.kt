package datn.datn_expansemanagement.screen.report.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel

interface ReportContract {
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterGetWallet(list: MutableList<ViewModel>)
        fun showListTransaction(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getData(
            idWallet: Int? = null,
            isCreditCard: Boolean = false,
            date: String? = null,
            data: GetWalletItemViewModel? = null
        )
        abstract fun getTransaction(idWallet: Int)
        abstract fun gotoReportDetailActivity(data: ReportViewModel)
        abstract fun getWalletForUser(idWallet: Int? = null, isCreditCard: Boolean = false)
    }
}