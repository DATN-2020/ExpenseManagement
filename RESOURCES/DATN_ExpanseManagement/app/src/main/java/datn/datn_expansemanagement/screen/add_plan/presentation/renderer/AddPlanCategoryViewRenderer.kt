package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanCategoryViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanDateViewModel
import kotlinx.android.synthetic.main.item_layout_add_plan_date.view.*

class AddPlanCategoryViewRenderer(
    context: Context,
    private val onChooseCategory: OnActionData<AddPlanCategoryViewModel>
) : ViewRenderer<AddPlanCategoryViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_plan_date
    }

    override fun getModelClass(): Class<AddPlanCategoryViewModel> =
        AddPlanCategoryViewModel::class.java

    override fun bindView(model: AddPlanCategoryViewModel, viewRoot: View) {
        viewRoot.edtName.text = model.name
        viewRoot.edtName.hint = "Chọn loại áp dụng"
        viewRoot.clTypeWallet.setOnClickListener {
            EventFireUtil.fireEvent(onChooseCategory, model)
        }
    }

}