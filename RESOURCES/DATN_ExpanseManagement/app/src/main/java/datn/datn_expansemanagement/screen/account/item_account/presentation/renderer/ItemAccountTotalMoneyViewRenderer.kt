package datn.datn_expansemanagement.screen.account.item_account.presentation.renderer

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.account.item_account.presentation.ItemAccountResource
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel
import kotlinx.android.synthetic.main.item_layout_account_total_money.view.*

class ItemAccountTotalMoneyViewRenderer(
    context: Context,
    private val mResouce: ItemAccountResource
) : ViewRenderer<ItemAccountTotalMoneyViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_account_total_money
    }

    override fun getModelClass(): Class<ItemAccountTotalMoneyViewModel> =
        ItemAccountTotalMoneyViewModel::class.java

    override fun bindView(model: ItemAccountTotalMoneyViewModel, viewRoot: View) {
        val money = Utils.formatMoneyVND(model.total)
        setTextColor(viewRoot.tvTotalMoney, money)
    }

    private fun setTextColor(view: TextView, taskName: String) {
        val stringDisplay = "Tổng tiền: $taskName"
        val spannableString = SpannableString(stringDisplay)
        val start = 11
        val end = start + taskName.length
        spannableString.setSpan(ForegroundColorSpan(mResouce.getColorName()), start, end, 0)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), start, end, 0)
        view.text = spannableString
    }

}