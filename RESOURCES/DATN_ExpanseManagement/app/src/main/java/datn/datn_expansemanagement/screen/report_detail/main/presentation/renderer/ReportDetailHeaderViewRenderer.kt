package datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailHeaderViewModel
import kotlinx.android.synthetic.main.item_layout_report_detail_header.view.*

class ReportDetailHeaderViewRenderer (context: Context): ViewRenderer<ReportDetailHeaderViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_detail_header
    }

    override fun getModelClass(): Class<ReportDetailHeaderViewModel> = ReportDetailHeaderViewModel::class.java

    override fun bindView(model: ReportDetailHeaderViewModel, viewRoot: View) {
        viewRoot.tvPriceReceive.text = Utils.formatMoney(model.priceReceive)
        viewRoot.tvPriceDonate.text = Utils.formatMoney(model.priceDonate)
        viewRoot.tvPriceBalance.text = Utils.formatMoney(model.priceReceive - model.priceDonate)
        viewRoot.llViewReport.gone()
    }

}