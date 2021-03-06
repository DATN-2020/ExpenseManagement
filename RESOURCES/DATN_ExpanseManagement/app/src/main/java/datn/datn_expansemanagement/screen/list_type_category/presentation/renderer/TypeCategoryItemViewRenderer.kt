package datn.datn_expansemanagement.screen.list_type_category.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.list_type_category.presentation.model.TypeCategoryItemViewModel
import kotlinx.android.synthetic.main.item_category.view.*

class TypeCategoryItemViewRenderer (context: Context): ViewRenderer<TypeCategoryItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_category
    }

    override fun getModelClass(): Class<TypeCategoryItemViewModel> = TypeCategoryItemViewModel::class.java

    override fun bindView(model: TypeCategoryItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(viewRoot.imgCategory, model.imageUrl, R.drawable.ic_add_category_icon)
        if(model.isChoose){
            viewRoot.imgChecked.visible()
        }else{
            viewRoot.imgChecked.gone()
        }

        if(model.isLast){
            viewRoot.viewBottom.invisible()
        }else{
            viewRoot.viewBottom.visible()
        }
        viewRoot.tvCategory.text = model.name
    }

}