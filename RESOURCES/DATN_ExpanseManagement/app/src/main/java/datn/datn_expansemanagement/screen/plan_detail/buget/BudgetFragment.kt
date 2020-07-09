package datn.datn_expansemanagement.screen.plan_detail.buget

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.presentation.BudgetView

class BudgetFragment(private val type: PlanItemViewModel?) : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return BudgetView(getMvpActivity(), BudgetView.ViewCreator(getMvpActivity(), null), type)
    }

}