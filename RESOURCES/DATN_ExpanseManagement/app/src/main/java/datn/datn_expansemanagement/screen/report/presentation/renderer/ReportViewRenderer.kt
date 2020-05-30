package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import kotlinx.android.synthetic.main.item_report.view.*

class ReportViewRenderer (context: Context): ViewRenderer<ReportViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_report
    }

    override fun getModelClass(): Class<ReportViewModel> = ReportViewModel::class.java

    override fun bindView(model: ReportViewModel, viewRoot: View) {
        viewRoot.tvReport.text = model.name
        when(model.type){
            ReportViewModel.TypeReport.RECEIVE->{

            }
            ReportViewModel.TypeReport.DONATE->{}
            ReportViewModel.TypeReport.FINANCE->{}
            ReportViewModel.TypeReport.LOAN->{}
            ReportViewModel.TypeReport.FRIEND->{}
            ReportViewModel.TypeReport.TRIP->{}
        }
    }

}