package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import kotlinx.android.synthetic.main.item_report.view.*

class ReportViewRenderer (context: Context): ViewRenderer<ReportViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_report
    }

    override fun getModelClass(): Class<ReportViewModel> = ReportViewModel::class.java

    override fun bindView(model: ReportViewModel, viewRoot: View) {
//        viewRoot.tvReport.text = model.name
//        when(model.type){
//            ReportViewModel.TypeReport.RECEIVE->{
//                GlideImageHelper(context).loadThumbnail(viewRoot.imgReport, R.drawable.ic_report_receive, R.drawable.ic_report_receive)
//            }
//            ReportViewModel.TypeReport.DONATE->{
//                GlideImageHelper(context).loadThumbnail(viewRoot.imgReport, R.drawable.ic_report_donate, R.drawable.ic_report_donate)
//            }
//            ReportViewModel.TypeReport.FINANCE->{
//                GlideImageHelper(context).loadThumbnail(viewRoot.imgReport, R.drawable.ic_report_finance, R.drawable.ic_report_finance)
//            }
//            ReportViewModel.TypeReport.LOAN->{
//                GlideImageHelper(context).loadThumbnail(viewRoot.imgReport, R.drawable.ic_report_loan, R.drawable.ic_report_loan)
//            }
//            ReportViewModel.TypeReport.FRIEND->{
//                GlideImageHelper(context).loadThumbnail(viewRoot.imgReport, R.drawable.ic_report_friends, R.drawable.ic_report_friends)
//            }
//            ReportViewModel.TypeReport.TRIP->{
//                GlideImageHelper(context).loadThumbnail(viewRoot.imgReport, R.drawable.ic_report_trip, R.drawable.ic_report_trip)
//            }
//        }
    }

}