package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNameItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_name_vholder.view.*

class AddWalletNameItemViewRenderer(context: Context, private val mResource: AddWalletResource) :
    ViewRenderer<AddWalletNameItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_name_vholder
    }

    override fun getModelClass(): Class<AddWalletNameItemViewModel> =
        AddWalletNameItemViewModel::class.java

    override fun bindView(model: AddWalletNameItemViewModel, viewRoot: View) {
        when (model.type) {
            AddWalletNameItemViewModel.Type.ACCOUNT_NAME -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgComment,
                    "",
                    R.drawable.ic_wallet
                )
                viewRoot.edtName.hint = mResource.getTextEnterName()
            }

            AddWalletNameItemViewModel.Type.PAY_TO -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgComment,
                    "",
                    R.drawable.ic_box
                )
                viewRoot.edtName.hint = mResource.getTextPayTo()
            }
        }
    }

}