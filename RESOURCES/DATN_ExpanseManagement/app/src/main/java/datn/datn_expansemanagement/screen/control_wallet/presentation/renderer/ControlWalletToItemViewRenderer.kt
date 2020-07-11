package datn.datn_expansemanagement.screen.control_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletToViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_type_vholder.view.*

class ControlWalletToItemViewRenderer (context: Context): ViewRenderer<ControlWalletToViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_type_vholder
    }

    override fun getModelClass(): Class<ControlWalletToViewModel> = ControlWalletToViewModel::class.java

    override fun bindView(model: ControlWalletToViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(
            viewRoot.imgTypeWallet,
            model.imgUrl,
            R.drawable.ic_default
        )
        viewRoot.edtName.text = model.name
        viewRoot.edtName.hint = "Chọn ví"
    }

}