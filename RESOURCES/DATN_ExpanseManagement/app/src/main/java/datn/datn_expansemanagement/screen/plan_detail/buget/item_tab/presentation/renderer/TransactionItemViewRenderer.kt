package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.TransactionItemViewModel
import kotlinx.android.synthetic.main.item_layout_plan_detail_transaction.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TransactionItemViewRenderer(
        context: Context,
        private val onActionData: OnActionData<TransactionItemViewModel>)
    : ViewRenderer<TransactionItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_plan_detail_transaction
    }

    override fun getModelClass(): Class<TransactionItemViewModel> = TransactionItemViewModel::class.java

    override fun bindView(model: TransactionItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(viewRoot.imgTypeExpense, model.imgUrl, R.drawable.ic_default)
        viewRoot.tvTitleTypeExpense.text = model.name
        viewRoot.tvMoneyTypeExpense.text = Utils.formatMoney(model.price)
        if (model.isFinish) {
            viewRoot.tvContentTypeExpense.text = "Đã kết thúc"

        } else {
            viewRoot.tvContentTypeExpense.text = "Lần xuất hiện tiếp theo: ${model.currentDate}"
        }

        viewRoot.clPeriodic.setOnLongClickListener {
            EventFireUtil.fireEvent(onActionData, model)
            true
        }
    }

    private fun getDayBetween2Day(startDay: String, dayEnd: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val startDiff = dateFormat.parse(startDay)
        val endDiff = dateFormat.parse(dayEnd)
        val diff = endDiff.time - startDiff.time
        return TimeUnit.MILLISECONDS.toDays(diff).toString()
    }

}