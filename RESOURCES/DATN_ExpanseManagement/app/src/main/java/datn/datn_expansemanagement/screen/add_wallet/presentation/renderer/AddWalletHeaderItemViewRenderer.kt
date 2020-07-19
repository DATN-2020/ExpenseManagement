package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_total_money.view.*

class AddWalletHeaderItemViewRenderer(
    context: Context,
    private val mResource: AddWalletResource,
    private val onUpdateTotal: OnActionData<AddWalletHeaderItemViewModel>? = null
) : ViewRenderer<AddWalletHeaderItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_total_money
    }

    override fun getModelClass(): Class<AddWalletHeaderItemViewModel> =
        AddWalletHeaderItemViewModel::class.java

    override fun bindView(model: AddWalletHeaderItemViewModel, viewRoot: View) {
        viewRoot.tvTitleTotalMoney.text = mResource.getTitleStartPrice()
        viewRoot.edtMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewRoot.edtMoney.removeTextChangedListener(this)
                if (!viewRoot.edtMoney.text.isNullOrEmpty()) {
                    viewRoot.edtMoney.setText(Utils.customFormatMoney(convertMoneyToDouble(s.toString())))
                    viewRoot.edtMoney.setSelection(viewRoot.edtMoney.text.toString().length)
                    model.price = convertMoneyToDouble(viewRoot.edtMoney.text.toString()).toDouble()
                    if (model.isAccumulation) {
                        EventFireUtil.fireEvent(onUpdateTotal, model)
                    }
                }
                viewRoot.edtMoney.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun convertMoneyToDouble(money: String): String {
        return money.replace(",", "")
    }
}