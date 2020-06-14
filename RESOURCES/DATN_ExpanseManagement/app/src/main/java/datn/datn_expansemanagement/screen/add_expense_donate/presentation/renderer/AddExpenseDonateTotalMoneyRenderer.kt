package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateTotalMoneyViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_total_money.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AddExpenseDonateTotalMoneyRenderer(
    context: Context,
    private val mResource: AddExpenseDonateResource
) :
    ViewRenderer<AddExpenseDonateTotalMoneyViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_total_money
    }

    override fun getModelClass(): Class<AddExpenseDonateTotalMoneyViewModel> =
        AddExpenseDonateTotalMoneyViewModel::class.java

    override fun bindView(model: AddExpenseDonateTotalMoneyViewModel, viewRoot: View) {
        if(model.isDonate){
            viewRoot.edtMoney.setHintTextColor(mResource.getColorTotalMoney())
        }else{
            viewRoot.edtMoney.setHintTextColor(mResource.getColorTotalMoneyReceive())
        }

        viewRoot.edtMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewRoot.edtMoney.removeTextChangedListener(this)
                if(!viewRoot.edtMoney.text.isNullOrEmpty()){
                    viewRoot.edtMoney.setText(Utils.customFormatMoney(s.toString()))
                    viewRoot.edtMoney.setSelection(viewRoot.edtMoney.text.toString().length)
                }
                val result = viewRoot.edtMoney.text.toString().replace(",","")
                AddExpenseFragment.model.totalMoney = result.toDouble()
                viewRoot.edtMoney.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}
