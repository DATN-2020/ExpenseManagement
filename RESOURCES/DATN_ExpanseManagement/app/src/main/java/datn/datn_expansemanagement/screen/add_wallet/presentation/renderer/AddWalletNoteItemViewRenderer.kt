package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNoteItemViewModel

class AddWalletNoteItemViewRenderer (context: Context): ViewRenderer<AddWalletNoteItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_note_vholder
    }

    override fun getModelClass(): Class<AddWalletNoteItemViewModel> = AddWalletNoteItemViewModel::class.java

    override fun bindView(model: AddWalletNoteItemViewModel, viewRoot: View) {
    }

}