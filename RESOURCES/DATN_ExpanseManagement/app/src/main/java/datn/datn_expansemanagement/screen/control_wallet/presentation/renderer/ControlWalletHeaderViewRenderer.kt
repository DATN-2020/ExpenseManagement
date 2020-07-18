package datn.datn_expansemanagement.screen.control_wallet.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletHeaderViewModel
import kotlinx.android.synthetic.main.item_layout_control_wallet_header_vholder.view.*

class ControlWalletHeaderViewRenderer (context: Context): ViewRenderer<ControlWalletHeaderViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_control_wallet_header_vholder
    }

    override fun getModelClass(): Class<ControlWalletHeaderViewModel> = ControlWalletHeaderViewModel::class.java

    override fun bindView(model: ControlWalletHeaderViewModel, viewRoot: View) {
        viewRoot.tvName.setText(model.nameWallet)

        viewRoot.tvTitleChooseDate.text = model.title
        viewRoot.edtMoney.setText(Utils.formatMoney(model.price))
        viewRoot.edtMoney.setSelection(viewRoot.edtMoney.text.toString().length)
        viewRoot.tvName.isEnabled = !model.isOtherWallet
        viewRoot.edtMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewRoot.edtMoney.removeTextChangedListener(this)
                if(!viewRoot.edtMoney.text.isNullOrEmpty()){
                    if(model.isOtherWallet){
                        var result = convertMoneyToDouble(s.toString()).toDouble()
                        if(result > model.maxPrice){
                            result = model.maxPrice
                        }
                        model.price = result
                    }else{
                        model.price = convertMoneyToDouble(s.toString()).toDouble()
                    }
                    viewRoot.edtMoney.setText(Utils.formatMoney(model.price))
                    viewRoot.edtMoney.setSelection(viewRoot.edtMoney.text.toString().length)
                }
                viewRoot.edtMoney.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        viewRoot.tvName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                model.nameWallet = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun convertMoneyToDouble(money: String): String{
        return money.replace(",","").replace(".", "")
    }

}