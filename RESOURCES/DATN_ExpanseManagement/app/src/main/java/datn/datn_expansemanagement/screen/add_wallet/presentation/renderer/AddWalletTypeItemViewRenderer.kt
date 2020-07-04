package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_type_vholder.view.*

class AddWalletTypeItemViewRenderer(context: Context, private val mResource : AddWalletResource,
private val onActionData: OnActionData<AddWalletTypeItemViewModel>) :
    ViewRenderer<AddWalletTypeItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_type_vholder
    }

    override fun getModelClass(): Class<AddWalletTypeItemViewModel> =
        AddWalletTypeItemViewModel::class.java

    override fun bindView(model: AddWalletTypeItemViewModel, viewRoot: View) {
        viewRoot.edtName.hint = model.hint
        viewRoot.edtName.text = model.name.getValueOrDefaultIsEmpty()
        when (model.type) {
            AddWalletTypeItemViewModel.Type.DEFAULT -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_type_wallet
                )
            }
            AddWalletTypeItemViewModel.Type.SAVING -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_account_balance_black_24dp
                )
            }
            AddWalletTypeItemViewModel.Type.START_DATE->{
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_date_range_black_24dp
                )
            }
            AddWalletTypeItemViewModel.Type.PERIOD->{
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeWallet,
                    "",
                    R.drawable.ic_date_range_black_24dp
                )
            }
        }

        viewRoot.clTypeWallet.setOnClickListener {
            EventFireUtil.fireEvent(onActionData, model)
        }
    }

}