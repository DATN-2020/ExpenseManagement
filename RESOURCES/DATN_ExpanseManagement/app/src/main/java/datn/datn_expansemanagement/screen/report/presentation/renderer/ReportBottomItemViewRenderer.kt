package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBottomItemViewModel
import kotlinx.android.synthetic.main.item_layout_report_bottom.view.*

class ReportBottomItemViewRenderer (context: Context): ViewRenderer<ReportBottomItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_bottom
    }

    override fun getModelClass(): Class<ReportBottomItemViewModel> = ReportBottomItemViewModel::class.java

    override fun bindView(model: ReportBottomItemViewModel, viewRoot: View) {
        viewRoot.tvPriceDue.text = Utils.formatMoney(model.priceDue)
        viewRoot.tvPriceBorrow.text = Utils.formatMoney(model.priceBorrow)
        viewRoot.tvPriceOther.text = Utils.formatMoney(model.priceOther)
    }

}