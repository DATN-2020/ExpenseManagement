package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.TransactionItemViewModel
import kotlinx.android.synthetic.main.item_layout_plan_detail_transaction.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TransactionItemViewRenderer (context: Context): ViewRenderer<TransactionItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_plan_detail_transaction
    }

    override fun getModelClass(): Class<TransactionItemViewModel> = TransactionItemViewModel::class.java

    override fun bindView(model: TransactionItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(viewRoot.imgTypeExpense, model.imgUrl, R.drawable.ic_default)
        viewRoot.tvTitleTypeExpense.text = model.name
        viewRoot.tvMoneyTypeExpense.text = Utils.formatMoney(model.price)

//        when(getDayBetween2Day())
    }

    private fun getDayBetween2Day(startDay: String,dayEnd: String): String{
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val startDiff = dateFormat.parse(startDay)
        val endDiff = dateFormat.parse(dayEnd)
        val diff = endDiff.time - startDiff.time
        return TimeUnit.MILLISECONDS.toDays(diff).toString()
    }

}