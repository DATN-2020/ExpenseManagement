package datn.datn_expansemanagement.screen.plan_detail.buget.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

interface BudgetContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun handleAfterGetWallet(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData(typePlan : PlanItemViewModel)
        abstract fun gotoAddPlanActivity(typeAdd: PlanItemViewModel)
        abstract fun getWalletForUser(idWallet: Int? = null)
    }
}