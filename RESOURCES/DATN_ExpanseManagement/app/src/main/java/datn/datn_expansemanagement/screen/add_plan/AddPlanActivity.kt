package datn.datn_expansemanagement.screen.add_plan

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_plan.presentation.AddPlanView
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

class AddPlanActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val typeAdd = intent?.getParcelableExtra<TypeAddViewModel>(TypeAddViewModel::class.java.simpleName)
        return AddPlanView(this, AddPlanView.ViewCreator(this, null), typeAdd)
    }

}