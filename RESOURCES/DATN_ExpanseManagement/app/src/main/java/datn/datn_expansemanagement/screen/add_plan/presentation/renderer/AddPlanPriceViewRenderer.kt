package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanDateViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanPriceViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_total_money.view.*
import kotlinx.android.synthetic.main.item_layout_add_plan_date.view.*

class AddPlanPriceViewRenderer(context: Context) : ViewRenderer<AddPlanPriceViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_total_money
    }

    override fun getModelClass(): Class<AddPlanPriceViewModel> = AddPlanPriceViewModel::class.java

    override fun bindView(model: AddPlanPriceViewModel, viewRoot: View) {
        viewRoot.tvTitleTotalMoney.text = model.title
        viewRoot.edtMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewRoot.edtMoney.removeTextChangedListener(this)
                if(!viewRoot.edtMoney.text.isNullOrEmpty()){
                    viewRoot.edtMoney.setText(Utils.customFormatMoney(s.toString()))
                    viewRoot.edtMoney.setSelection(viewRoot.edtMoney.text.toString().length)
                    model.price = convertMoneyToDouble(viewRoot.edtMoney.text.toString()).toDouble()
                }else{
                    model.price = null
                }
                viewRoot.edtMoney.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun convertMoneyToDouble(money: String): String{
        return money.replace(",","")
    }
}