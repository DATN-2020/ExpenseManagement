package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBalanceViewModel
import kotlinx.android.synthetic.main.item_layout_report_balance_receive_donate.view.*

class ReportBalanceViewRenderer (context: Context): ViewRenderer<ReportBalanceViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_balance_receive_donate
    }

    override fun getModelClass(): Class<ReportBalanceViewModel> = ReportBalanceViewModel::class.java

    override fun bindView(model: ReportBalanceViewModel, viewRoot: View) {
        viewRoot.tvPriceBeginBalance.text = Utils.formatMoney(model.priceReceive)
        viewRoot.tvPriceEndBalance.text = Utils.formatMoney(model.priceDonate)
    }

}