package datn.datn_expansemanagement.screen.main_plan.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel

interface OverviewContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData()
        abstract fun gotoExchangeRateActivity()
        abstract fun gotoHistoryActivity()
        abstract fun gotoPlanDetailActivity(planType: PlanItemViewModel)
    }
}