package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNameItemViewModel

class AddWalletNameItemViewRenderer(context: Context) :
    ViewRenderer<AddWalletNameItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_name_vholder
    }

    override fun getModelClass(): Class<AddWalletNameItemViewModel> = AddWalletNameItemViewModel::class.java

    override fun bindView(model: AddWalletNameItemViewModel, viewRoot: View) {

    }

}