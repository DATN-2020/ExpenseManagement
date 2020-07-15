package datn.datn_expansemanagement.screen.report

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.report.presentation.ReportView

class ReportFragment(private val idWallet: Int? = null, private val isCard: Boolean = false) : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ReportView(getMvpActivity(), ReportView.ViewCreator(getMvpActivity(), null), idWallet, isCard)
    }
}