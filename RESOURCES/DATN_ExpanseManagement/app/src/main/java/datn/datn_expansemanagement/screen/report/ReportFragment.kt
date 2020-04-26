package datn.datn_expansemanagement.screen.report

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.report.presentation.ReportView

class ReportFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ReportView(getMvpActivity(), ReportView.ViewCreator(getMvpActivity(), null))
    }
}