package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BillItemViewModel
import kotlinx.android.synthetic.main.item_layout_plan_detail_bill.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class BillItemViewRenderer(
    context: Context,
    private val onActionData: OnActionData<BillItemViewModel>,
    private val onLongClick: OnActionData<BillItemViewModel>
) : ViewRenderer<BillItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_plan_detail_bill
    }

    override fun getModelClass(): Class<BillItemViewModel> = BillItemViewModel::class.java

    override fun bindView(model: BillItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(
            viewRoot.imgTypeBill,
            model.image,
            R.drawable.ic_default
        )
        viewRoot.tvTitleBill.text = model.name
        if(model.isPay){
            viewRoot.tvContentBill.text = "Đã kết thúc ngày ${model.dateE}"

            viewRoot.tvTimeLimitBill.text = "Đã thanh toán"
            viewRoot.btnPayBill.text = "Đã thanh toán"
            viewRoot.btnPayBill.isEnabled = false
        }else{
            viewRoot.tvContentBill.text = "Hóa đơn tiếp theo xuất hiện ngày ${model.currentDate}"
            viewRoot.btnPayBill.isEnabled = true
            val day = getDayBetween2Day(model.dateE).toInt()
            when {
                day > 1 -> {
                    viewRoot.tvTimeLimitBill.text =
                        "Hết hạn trong ${getDayBetween2Day(model.dateE)} ngày"
                }
                day == 0 -> {
                    viewRoot.tvTimeLimitBill.text = "Đến hạn"
                }
                else -> {
                    viewRoot.tvTimeLimitBill.text = "Quá hạn"
                }
            }
            viewRoot.btnPayBill.text = "Trả ${Utils.formatMoney(model.amount)}"
        }

        viewRoot.btnPayBill.setOnClickListener {
            EventFireUtil.fireEvent(onActionData, model)
        }

        viewRoot.clBill.setOnLongClickListener {
            EventFireUtil.fireEvent(onLongClick, model)
            true
        }
    }

    private fun getDayBetween2Day(dayEnd: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDay = dateFormat.format(Date())
        val currentDiff = dateFormat.parse(currentDay)

        val endDiff = dateFormat.parse(dayEnd)
        val diff = endDiff.time - currentDiff.time

        return TimeUnit.MILLISECONDS.toDays(diff).toString()
    }

}