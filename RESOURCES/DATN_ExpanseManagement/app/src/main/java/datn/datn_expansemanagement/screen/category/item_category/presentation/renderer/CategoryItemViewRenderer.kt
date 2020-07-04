package datn.datn_expansemanagement.screen.category.item_category.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.CategoryItemViewModel
import kotlinx.android.synthetic.main.item_category_vholder.view.*

class CategoryItemViewRenderer (context: Context): ViewRenderer<CategoryItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_category_vholder
    }

    override fun getModelClass(): Class<CategoryItemViewModel> = CategoryItemViewModel::class.java

    override fun bindView(model: CategoryItemViewModel, viewRoot: View) {
        viewRoot.tvCategory.text = model.name
        GlideImageHelper(context).loadThumbnail(viewRoot.imgCategory, model.imgUrl, R.drawable.ic_default)
    }
}