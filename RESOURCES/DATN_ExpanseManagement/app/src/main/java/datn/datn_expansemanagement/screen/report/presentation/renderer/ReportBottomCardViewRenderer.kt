package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBottomCardViewModel
import kotlinx.android.synthetic.main.item_layout_report_detail_header.view.*

class ReportBottomCardViewRenderer(
    context: Context,
    private val onActionNotify: OnActionNotify
) :
    ViewRenderer<ReportBottomCardViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_detail_header
    }

    override fun getModelClass(): Class<ReportBottomCardViewModel> =
        ReportBottomCardViewModel::class.java

    override fun bindView(model: ReportBottomCardViewModel, viewRoot: View) {
        viewRoot.tvPriceReceive.text = Utils.formatMoney(model.priceReceive)
        viewRoot.tvPriceDonate.text = Utils.formatMoney(model.priceDonate)
        viewRoot.tvPriceBalance.text = Utils.formatMoney(model.priceReceive - model.priceDonate)

        viewRoot.btnViewReport.text = "Xem lịch sử giao dịch"
        viewRoot.btnViewReport.setOnClickListener {
            EventFireUtil.fireEvent(onActionNotify)
        }
    }

}