package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.screen.report.presentation.model.ReportHeaderCardViewModel
import kotlinx.android.synthetic.main.item_layout_report_header_credit_card.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ReportHeaderCardViewRenderer(context: Context) :
    ViewRenderer<ReportHeaderCardViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_header_credit_card
    }

    override fun getModelClass(): Class<ReportHeaderCardViewModel> =
        ReportHeaderCardViewModel::class.java

    override fun bindView(model: ReportHeaderCardViewModel, viewRoot: View) {
        viewRoot.tvPrice.text = Utils.formatMoney(model.price)
        viewRoot.tvValueDate.text = Utils.convertDateFormat(
            model.date, SimpleDateFormat("yyyy-MM-dd"),
            SimpleDateFormat
                ("dd/MM/yyyy")
        )
        if (!model.isFinish) {
            viewRoot.tvDayLeft.text = "Còn lại ${getDayBetween2Day(model.date)} ngày"
        } else {
            viewRoot.tvDayLeft.invisible()
        }
    }

    private fun getDayBetween2Day(dayEnd: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val currentDay = dateFormat.format(Date())
        val currentDiff = dateFormat.parse(currentDay)

        val endDiff = dateFormat.parse(dayEnd)
        val diff = endDiff.time - currentDiff.time

        return TimeUnit.MILLISECONDS.toDays(diff).toString()
    }


}