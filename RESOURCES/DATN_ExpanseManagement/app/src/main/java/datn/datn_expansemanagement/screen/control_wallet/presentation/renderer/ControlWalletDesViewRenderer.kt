package datn.datn_expansemanagement.screen.control_wallet.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletDesViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_note_vholder.view.*

class ControlWalletDesViewRenderer (context: Context): ViewRenderer<ControlWalletDesViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_note_vholder
    }

    override fun getModelClass(): Class<ControlWalletDesViewModel> = ControlWalletDesViewModel::class.java

    override fun bindView(model: ControlWalletDesViewModel, viewRoot: View) {
        viewRoot.edtName.setText(model.des.getValueOrDefaultIsEmpty())
        viewRoot.edtName.hint = "Ghi chú"
        viewRoot.edtName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                model.des = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }
}