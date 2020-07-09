package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportNetIncomeViewModel
import kotlinx.android.synthetic.main.item_layout_report_net_income.view.*

class ReportNetIncomeViewRenderer(context: Context) :
    ViewRenderer<ReportNetIncomeViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_net_income
    }

    override fun getModelClass(): Class<ReportNetIncomeViewModel> =
        ReportNetIncomeViewModel::class.java

    override fun bindView(model: ReportNetIncomeViewModel, viewRoot: View) {
        viewRoot.tvPriceNetIncome.text = Utils.formatMoney(model.priceNetIncome)
    }
}