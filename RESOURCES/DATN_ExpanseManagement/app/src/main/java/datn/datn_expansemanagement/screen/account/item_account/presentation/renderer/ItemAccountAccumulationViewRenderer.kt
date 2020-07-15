package datn.datn_expansemanagement.screen.account.item_account.presentation.renderer

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.*
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.sbPercent
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.tvAccumulation
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.tvRest
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.tvWallet
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.viewBottom
import kotlinx.android.synthetic.main.item_layout_plan_detail_budget.view.*

class ItemAccountAccumulationViewRenderer(
    context: Context,
    private val onActionClickMore: OnActionData<ItemAccountAccumulationViewModel>,
    private val onClickItem: OnActionData<ItemAccountAccumulationViewModel>
) : ViewRenderer<ItemAccountAccumulationViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_account_accumulation
    }

    override fun getModelClass(): Class<ItemAccountAccumulationViewModel> =
        ItemAccountAccumulationViewModel::class.java

    @SuppressLint("ResourceAsColor")
    override fun bindView(model: ItemAccountAccumulationViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        var money = Utils.formatMoney(model.moneyAccumulation)
        viewRoot.tvAccumulation.text = money
        val currentMoney = Utils.formatMoney(model.moneyCurrent)
        viewRoot.tvCurrent.text = "Hiện tại: $currentMoney"
        val moneyRest = Utils.formatMoney(model.moneyAccumulation - model.moneyCurrent)
        viewRoot.tvRest.text = "Cần thêm: $moneyRest"

        viewRoot.sbPercent.progress = ((model.moneyCurrent / model.moneyAccumulation) * 100).toInt()
        if (model.moneyCurrent >= model.moneyAccumulation / 2) {
            viewRoot.tvCurrent.setTextColor(R.color.color_51c471)
        } else {
            viewRoot.tvCurrent.setTextColor(R.color.color_219dfd)
        }

        if (model.isLast) {
            viewRoot.viewBottom.gone()
        } else {
            viewRoot.viewBottom.visible()
        }

        viewRoot.imgMore.setOnClickListener {
            EventFireUtil.fireEvent(onActionClickMore, model)
        }

        viewRoot.clItem.setOnClickListener {
            EventFireUtil.fireEvent(onClickItem, model)
        }
    }

}