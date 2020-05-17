package datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveCategoryViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_choose_category.view.*

class AddExpenseReceiveCategoryRenderer (context: Context,
private val onChooseCategory: OnActionData<AddExpenseReceiveCategoryViewModel>): ViewRenderer<AddExpenseReceiveCategoryViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_choose_category
    }

    override fun getModelClass(): Class<AddExpenseReceiveCategoryViewModel> = AddExpenseReceiveCategoryViewModel::class.java

    override fun bindView(model: AddExpenseReceiveCategoryViewModel, viewRoot: View) {
        viewRoot.tvChooseCategory.setOnClickListener {
            EventFireUtil.fireEvent(onChooseCategory, model)
        }
    }

}