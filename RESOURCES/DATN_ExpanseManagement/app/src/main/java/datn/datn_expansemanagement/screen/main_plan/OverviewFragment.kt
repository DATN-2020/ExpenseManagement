package datn.datn_expansemanagement.screen.main_plan

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.main_plan.presentation.OverviewView

class OverviewFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return OverviewView(getMvpActivity(), OverviewView.ViewCreator(getMvpActivity(), null))
    }
}