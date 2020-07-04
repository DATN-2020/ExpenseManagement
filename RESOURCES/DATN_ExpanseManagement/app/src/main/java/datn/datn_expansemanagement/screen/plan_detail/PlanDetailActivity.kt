package datn.datn_expansemanagement.screen.plan_detail

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.PlanDetailView

class PlanDetailActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val typePlan  = intent?.getParcelableExtra<PlanItemViewModel>(PlanItemViewModel::class.java.simpleName)
        return PlanDetailView(this, PlanDetailView.ViewCreator(this, null), typePlan)
    }

}