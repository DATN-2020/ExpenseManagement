package datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.AddExpenseReceiveResource
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveTotalMoneyViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_total_money.view.*

class AddExpenseReceiveTotalMoneyRenderer (context: Context,
private val mResource : AddExpenseReceiveResource
): ViewRenderer<AddExpenseReceiveTotalMoneyViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_total_money
    }

    override fun getModelClass(): Class<AddExpenseReceiveTotalMoneyViewModel> = AddExpenseReceiveTotalMoneyViewModel::class.java

    override fun bindView(model: AddExpenseReceiveTotalMoneyViewModel, viewRoot: View) {
        viewRoot.edtMoney.setHintTextColor(mResource.getColorTotalMoney())
    }

}