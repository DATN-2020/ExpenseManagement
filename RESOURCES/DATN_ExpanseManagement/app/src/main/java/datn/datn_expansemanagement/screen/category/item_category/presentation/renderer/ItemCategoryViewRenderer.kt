package datn.datn_expansemanagement.screen.category.item_category.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemCategoryViewModel
import kotlinx.android.synthetic.main.item_category.view.*

class ItemCategoryViewRenderer(context: Context,
private val onChooseCategory: OnActionData<ItemCategoryViewModel>): ViewRenderer<ItemCategoryViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_category
    }

    override fun getModelClass(): Class<ItemCategoryViewModel> = ItemCategoryViewModel::class.java

    override fun bindView(model: ItemCategoryViewModel, viewRoot: View) {
        viewRoot.tvCategory.text = model.name
        viewRoot.clItemCategory.setOnClickListener {
            EventFireUtil.fireEvent(onChooseCategory, model)
        }
    }

}