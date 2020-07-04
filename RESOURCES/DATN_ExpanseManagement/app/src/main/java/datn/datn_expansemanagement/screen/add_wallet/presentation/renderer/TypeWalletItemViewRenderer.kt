package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.TypeWalletItemViewModel
import kotlinx.android.synthetic.main.item_category_vholder.view.*

class TypeWalletItemViewRenderer(context: Context) :
    ViewRenderer<TypeWalletItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_category_vholder
    }

    override fun getModelClass(): Class<TypeWalletItemViewModel> =
        TypeWalletItemViewModel::class.java

    override fun bindView(model: TypeWalletItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(
            viewRoot.imgCategory,
            model.img,
            R.drawable.ic_default
        )

        viewRoot.tvCategory.text = model.name
    }
}