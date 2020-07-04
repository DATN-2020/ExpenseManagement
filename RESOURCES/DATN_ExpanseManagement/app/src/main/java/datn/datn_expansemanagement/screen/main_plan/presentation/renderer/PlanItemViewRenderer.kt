package datn.datn_expansemanagement.screen.main_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import kotlinx.android.synthetic.main.item_layout_overview_total_money.view.*

class PlanItemViewRenderer(
    context: Context,
    private val onAction: OnActionData<PlanItemViewModel>
) : ViewRenderer<PlanItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_overview_total_money
    }

    override fun getModelClass(): Class<PlanItemViewModel> = PlanItemViewModel::class.java

    override fun bindView(model: PlanItemViewModel, viewRoot: View) {
        when (model.type) {
            PlanItemViewModel.Type.BUDGET -> {
                GlideImageHelper(context).loadThumbnail(viewRoot.imgLeft, "", R.drawable.ic_budget)
            }
            PlanItemViewModel.Type.TRANSACTION -> {
                GlideImageHelper(context).loadThumbnail(viewRoot.imgLeft, "", R.drawable.ic_transaction)
            }
            else -> {
                GlideImageHelper(context).loadThumbnail(viewRoot.imgLeft, "", R.drawable.ic_bill)
            }
        }
        viewRoot.tvChooseCategory.text = model.name
        viewRoot.clItemPlan.setOnClickListener {
            EventFireUtil.fireEvent(onAction, model)
        }
    }
}