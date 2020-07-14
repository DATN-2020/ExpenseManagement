package datn.datn_expansemanagement.screen.add_plan.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

interface AddPlanContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun showListTime(list: MutableList<ViewModel>)
        fun handleAfterGetWallet(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData(typeAdd: PlanItemViewModel?)
        abstract fun getTime()
        abstract fun getWalletForUser(idWallet: Int? = null)
        abstract fun gotoCategoryActivity(id: Int? = null)
    }
}