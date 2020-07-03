package datn.datn_expansemanagement.screen.report_detail.main

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.ReportDetailView

class ReportDetailActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
//        val data = intent.getParcelableExtra<ReportViewModel>(ReportViewModel::class.java.simpleName)
        return ReportDetailView(this, ReportDetailView.ViewCreator(this, null))
    }

}