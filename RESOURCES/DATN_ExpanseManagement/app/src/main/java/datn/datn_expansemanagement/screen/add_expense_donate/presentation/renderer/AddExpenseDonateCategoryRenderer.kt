package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateCategoryViewModel

class AddExpenseDonateCategoryRenderer(context: Context) :
    ViewRenderer<AddExpenseDonateCategoryViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_choose_category
    }

    override fun getModelClass(): Class<AddExpenseDonateCategoryViewModel> =
        AddExpenseDonateCategoryViewModel::class.java

    override fun bindView(model: AddExpenseDonateCategoryViewModel, viewRoot: View) {
    }

}