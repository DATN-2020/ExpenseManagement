package datn.datn_expansemanagement.screen.control_wallet.presentation.renderer

import android.content.Context
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
    }
}