package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.presentation.model.ReportProcessCardViewModel
import kotlinx.android.synthetic.main.item_layout_report_process_credit_card.view.*
import java.text.SimpleDateFormat

class ReportProcessCardViewRenderer (context: Context): ViewRenderer<ReportProcessCardViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_process_credit_card
    }

    override fun getModelClass(): Class<ReportProcessCardViewModel> = ReportProcessCardViewModel::class.java

    override fun bindView(model: ReportProcessCardViewModel, viewRoot: View) {
        viewRoot.tvPriceBeginBalance.text = Utils.formatMoney(model.currentPrice)
        viewRoot.tvPriceEndBalance.text = Utils.convertDateFormat(model.endDate.getValueOrDefaultIsEmpty(), SimpleDateFormat("yyyy-MM-dd"), SimpleDateFormat("dd/MM/yyyy"))
        viewRoot.sbPercent.progress = model.progress
    }

}