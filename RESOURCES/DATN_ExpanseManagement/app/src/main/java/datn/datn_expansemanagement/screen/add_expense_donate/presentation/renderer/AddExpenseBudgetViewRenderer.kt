package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseBudgetViewModel
import kotlinx.android.synthetic.main.item_layout_add_plan_date.view.*

class AddExpenseBudgetViewRenderer(
    context: Context,
    private val onChooseBudget: OnActionData<AddExpenseBudgetViewModel>
) : ViewRenderer<AddExpenseBudgetViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_plan_date
    }

    override fun getModelClass(): Class<AddExpenseBudgetViewModel> =
        AddExpenseBudgetViewModel::class.java

    override fun bindView(model: AddExpenseBudgetViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(viewRoot.imgTypeWallet, "", R.drawable.ic_budget)
        viewRoot.edtName.text = model.name
        viewRoot.edtName.hint = "Chọn ngân sách áp dụng"
        viewRoot.clTypeWallet.setOnClickListener {
            EventFireUtil.fireEvent(onChooseBudget, model)
        }
    }

}