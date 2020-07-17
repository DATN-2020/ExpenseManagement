package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.PlanDetailResource
import kotlinx.android.synthetic.main.item_layout_plan_detail_budget.view.*

class BudgetItemViewRenderer(
    context: Context,
    private val mResource: PlanDetailResource
) : ViewRenderer<BudgetItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_plan_detail_budget
    }

    override fun getModelClass(): Class<BudgetItemViewModel> = BudgetItemViewModel::class.java

    override fun bindView(model: BudgetItemViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        viewRoot.tvAccumulation.text = Utils.formatMoney(model.totalPrice)
        viewRoot.sbPercent.progress = ((model.currentPrice / model.totalPrice) * 100).toInt()
        viewRoot.tvRest.text ="Đã dùng ".plus(Utils.formatMoney(model.currentPrice))
        GlideImageHelper(context).loadThumbnail(viewRoot.ivWallet, model.imgUrl, R.drawable.ic_default)

        when {
            model.currentPrice > model.totalPrice -> {
                viewRoot.sbPercent.progressDrawable.setColorFilter(mResource.getColorOutMax(), PorterDuff.Mode.SRC_IN)
                viewRoot.tvRest.setTextColor(mResource.getColorOutMax())
            }
            ((model.currentPrice / model.totalPrice) * 100).toInt() >= 50 -> {
                viewRoot.tvRest.setTextColor(mResource.getColorNormal())
            }
            else -> {
                viewRoot.tvRest.setTextColor(mResource.getColorLow())
            }
        }
    }

}