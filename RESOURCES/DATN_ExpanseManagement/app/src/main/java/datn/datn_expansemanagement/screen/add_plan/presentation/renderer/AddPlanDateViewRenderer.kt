package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanDateViewModel
import kotlinx.android.synthetic.main.item_layout_add_plan_date.view.*

class AddPlanDateViewRenderer(context: Context) : ViewRenderer<AddPlanDateViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_plan_date
    }

    override fun getModelClass(): Class<AddPlanDateViewModel> = AddPlanDateViewModel::class.java

    override fun bindView(model: AddPlanDateViewModel, viewRoot: View) {
        viewRoot.edtName.text = model.date
        viewRoot.edtName.hint = "Chọn thời gian áp dụng"
    }

}