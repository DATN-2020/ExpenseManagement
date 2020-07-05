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
        viewRoot.tvName.text = model.nameWallet
        viewRoot.edtMoney.setText(Utils.formatMoney(model.price))
        viewRoot.edtMoney.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewRoot.edtMoney.removeTextChangedListener(this)
                if(!viewRoot.edtMoney.text.isNullOrEmpty()){
                    viewRoot.edtMoney.setText(Utils.customFormatMoney(s.toString()))
                    viewRoot.edtMoney.setSelection(viewRoot.edtMoney.text.toString().length)
                }
                viewRoot.edtMoney.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

}