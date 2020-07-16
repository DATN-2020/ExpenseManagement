package datn.datn_expansemanagement.screen.category.item_category.presentation.renderer

import android.view.View
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.CategoryItemViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemTypeCategoryViewModel
import kotlinx.android.synthetic.main.item_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class TypeCategoryItemViewRenderer(
    private val mvpActivity: MvpActivity,
    private val onActionData: OnActionData<ViewModel>,
    private val onActionShow: OnActionData<ItemTypeCategoryViewModel>
) : ViewRenderer<ItemTypeCategoryViewModel>(mvpActivity) {
    override fun getLayoutId(): Int {
        return R.layout.item_category
    }

    override fun getModelClass(): Class<ItemTypeCategoryViewModel> =
        ItemTypeCategoryViewModel::class.java

    override fun bindView(model: ItemTypeCategoryViewModel, viewRoot: View) {
        viewRoot.tvCategory.text = model.name
        val renderInput = LinearRenderConfigFactory.Input(
            context = mvpActivity,
            orientation = LinearRenderConfigFactory.Orientation.VERTICAL
        )
        GlideImageHelper(context).loadThumbnail(viewRoot.imgCategory, model.imgUrl, R.drawable.ic_default)
        val renderConfig = LinearRenderConfigFactory(renderInput).create()

        val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel>{
            override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
                dataItem as CategoryItemViewModel
                dataItem.isChoose = true
                EventFireUtil.fireEvent(onActionData, dataItem)
            }

        }
        val listViewMvp = ListViewMvp(mvpActivity, viewRoot.rvCategoryItem, renderConfig)
        listViewMvp.addViewRenderer(CategoryItemViewRenderer(mvpActivity))
        listViewMvp.setOnItemRvClickedListener(onItemRvClickedListener)
        listViewMvp.createView()

        listViewMvp.setItems(model.listItem)
        listViewMvp.notifyDataChanged()

        if (!model.isShowChill) {
            listViewMvp.setItems(mutableListOf())
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgShowChild,
                "",
                R.drawable.ic_keyboard_arrow_down_blue_24dp
            )
            listViewMvp.notifyDataChanged()
        } else {
            listViewMvp.setItems(model.listItem)
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgShowChild,
                "",
                R.drawable.ic_keyboard_arrow_up_blue_24dp
            )
            listViewMvp.notifyDataChanged()
        }

        viewRoot.imgShowChild.setOnClickListener {
            EventFireUtil.fireEvent(onActionShow, model)
        }
    }

}