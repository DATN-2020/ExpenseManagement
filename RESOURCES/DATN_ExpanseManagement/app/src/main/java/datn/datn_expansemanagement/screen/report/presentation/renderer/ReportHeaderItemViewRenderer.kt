package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportHeaderItemViewModel
import kotlinx.android.synthetic.main.item_layout_report_header.view.*

class ReportHeaderItemViewRenderer(context: Context) :
    ViewRenderer<ReportHeaderItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_header
    }

    override fun getModelClass(): Class<ReportHeaderItemViewModel> =
        ReportHeaderItemViewModel::class.java

    override fun bindView(model: ReportHeaderItemViewModel, viewRoot: View) {
        viewRoot.tvPriceBeginBalance.text = Utils.formatMoney(model.beginBalance)
        viewRoot.tvPriceEndBalance.text = Utils.formatMoney(model.endBalance)
    }

}