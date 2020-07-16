package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanDateViewModel
import kotlinx.android.synthetic.main.item_layout_add_plan_date.view.*

class AddPlanDateViewRenderer(context: Context,
private val onChooseTime: OnActionData<AddPlanDateViewModel>) : ViewRenderer<AddPlanDateViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_plan_date
    }

    override fun getModelClass(): Class<AddPlanDateViewModel> = AddPlanDateViewModel::class.java

    override fun bindView(model: AddPlanDateViewModel, viewRoot: View) {
        viewRoot.edtName.text = model.date
        viewRoot.edtName.hint = "Chọn thời gian lặp lại"
        viewRoot.clTypeWallet.setOnClickListener{
            EventFireUtil.fireEvent(onChooseTime, model)
        }
    }

}