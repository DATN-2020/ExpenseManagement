package datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailDayViewModel
import kotlinx.android.synthetic.main.item_history_date.view.*

class ReportDetailDayViewRenderer (context: Context): ViewRenderer<ReportDetailDayViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_history_date
    }

    override fun getModelClass(): Class<ReportDetailDayViewModel> = ReportDetailDayViewModel::class.java

    override fun bindView(model: ReportDetailDayViewModel, viewRoot: View) {
        viewRoot.tvNumberDay.text = model.numberDay.toString()
        viewRoot.tvDay.text = model.dayOfWeek
        viewRoot.tvMonth.text = model.month
        viewRoot.tvTotalMoneyIncome.text = Utils.formatMoney(model.inCome)
        viewRoot.tvTotalMoneyOutcome.text = Utils.formatMoney(model.outCome)
    }
}
