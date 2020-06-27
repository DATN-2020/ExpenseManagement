package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel

class AddWalletTypeItemViewRenderer (context: Context): ViewRenderer<AddWalletTypeItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_type_vholder
    }

    override fun getModelClass(): Class<AddWalletTypeItemViewModel> = AddWalletTypeItemViewModel::class.java

    override fun bindView(model: AddWalletTypeItemViewModel, viewRoot: View) {

    }

}