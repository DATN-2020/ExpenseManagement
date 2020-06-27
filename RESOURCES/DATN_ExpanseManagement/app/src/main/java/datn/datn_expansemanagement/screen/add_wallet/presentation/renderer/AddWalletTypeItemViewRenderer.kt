package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_type_vholder.view.*

class AddWalletTypeItemViewRenderer(context: Context, private val mResource : AddWalletResource) :
    ViewRenderer<AddWalletTypeItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_type_vholder
    }

    override fun getModelClass(): Class<AddWalletTypeItemViewModel> =
        AddWalletTypeItemViewModel::class.java

    override fun bindView(model: AddWalletTypeItemViewModel, viewRoot: View) {
        when (model.type) {
            AddWalletTypeItemViewModel.Type.DEFAULT -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_type_wallet
                )
                viewRoot.edtName.hint = mResource.getHintTypeWallet()
            }
            AddWalletTypeItemViewModel.Type.SAVING -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_account_balance_black_24dp
                )
                viewRoot.edtName.hint = mResource.getHintBank()
            }
            AddWalletTypeItemViewModel.Type.START_DATE->{
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_date_range_black_24dp
                )
                viewRoot.edtName.hint = mResource.getStartDateSent()
            }
            AddWalletTypeItemViewModel.Type.PERIOD->{
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_date_range_black_24dp
                )
                viewRoot.edtName.hint = mResource.getPeriod()
            }
        }
    }

}