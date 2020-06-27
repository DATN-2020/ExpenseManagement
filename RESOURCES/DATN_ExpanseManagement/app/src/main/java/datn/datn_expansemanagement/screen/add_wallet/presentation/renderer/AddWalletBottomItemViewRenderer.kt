package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletBottomItemViewModel

class AddWalletBottomItemViewRenderer (context: Context): ViewRenderer<AddWalletBottomItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_bottom_vholder
    }

    override fun getModelClass(): Class<AddWalletBottomItemViewModel> = AddWalletBottomItemViewModel::class.java

    override fun bindView(model: AddWalletBottomItemViewModel, viewRoot: View) {
    }

}