package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanWalletViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_type_vholder.view.*

class AddPlanWalletViewRenderer(context: Context) : ViewRenderer<AddPlanWalletViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_type_vholder
    }

    override fun getModelClass(): Class<AddPlanWalletViewModel> = AddPlanWalletViewModel::class.java

    override fun bindView(model: AddPlanWalletViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(
            viewRoot.imgTypeWallet,
            model.imgUrl,
            R.drawable.ic_default
        )
        viewRoot.edtName.text = model.name
        viewRoot.edtName.hint = "Chọn ví áp dụng"
    }

}