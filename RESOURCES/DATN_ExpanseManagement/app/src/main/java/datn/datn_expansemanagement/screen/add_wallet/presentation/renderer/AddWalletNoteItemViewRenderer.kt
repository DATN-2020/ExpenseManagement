package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNoteItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_note_vholder.view.*

class AddWalletNoteItemViewRenderer (context: Context): ViewRenderer<AddWalletNoteItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_note_vholder
    }

    override fun getModelClass(): Class<AddWalletNoteItemViewModel> = AddWalletNoteItemViewModel::class.java

    override fun bindView(model: AddWalletNoteItemViewModel, viewRoot: View) {
        viewRoot.edtName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                model.note = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

}