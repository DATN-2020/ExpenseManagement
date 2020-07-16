package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanChooseDateViewModel
import kotlinx.android.synthetic.main.item_layout_add_plan_choose_date.view.*

class AddPlanChooseDateViewRenderer(
    context: Context,
    private val onChooseDate: OnActionData<AddPlanChooseDateViewModel>
) : ViewRenderer<AddPlanChooseDateViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_plan_choose_date
    }

    override fun getModelClass(): Class<AddPlanChooseDateViewModel> =
        AddPlanChooseDateViewModel::class.java

    override fun bindView(model: AddPlanChooseDateViewModel, viewRoot: View) {
        viewRoot.edtName.hint = "Chọn thời gian áp dụng"
        if (!model.startDate.isNullOrEmpty() && !model.endDate.isNullOrEmpty()) {
            viewRoot.edtName.text = "Từ ${model.startDate} đến ${model.endDate}"
        }
        viewRoot.clTypeWallet.setOnClickListener {
            EventFireUtil.fireEvent(onChooseDate, model)
        }
    }

}