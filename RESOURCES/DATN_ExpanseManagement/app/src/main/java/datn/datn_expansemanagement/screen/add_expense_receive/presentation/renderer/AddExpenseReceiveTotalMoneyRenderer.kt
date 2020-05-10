package datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveTotalMoneyViewModel

class AddExpenseReceiveTotalMoneyRenderer (context: Context): ViewRenderer<AddExpenseReceiveTotalMoneyViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_total_money
    }

    override fun getModelClass(): Class<AddExpenseReceiveTotalMoneyViewModel> = AddExpenseReceiveTotalMoneyViewModel::class.java

    override fun bindView(modelReceive: AddExpenseReceiveTotalMoneyViewModel, viewRoot: View) {

    }

}