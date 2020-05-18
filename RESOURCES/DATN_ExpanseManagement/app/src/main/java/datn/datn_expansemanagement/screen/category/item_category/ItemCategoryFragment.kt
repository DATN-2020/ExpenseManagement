package datn.datn_expansemanagement.screen.category.item_category

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.ItemCategoryView

class ItemCategoryFragment : MvpFragment(){

    companion object {
        private const val KEY_TAB = "KEY_TAB"
        private const val CATEGORY_ID = "CATEGORY_ID"

        fun newInstance(data: ViewModel, categoryId: Int? = null): ItemCategoryFragment {
            val recipeTabFragment = ItemCategoryFragment()
            val bundle = Bundle()
            categoryId?.let { bundle.putInt(CATEGORY_ID, it) }
            if(data is TabItemViewModel){
                bundle.putInt(KEY_TAB, data.id)
            }
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val tabId = arguments?.getInt(KEY_TAB)
        val categoryId = arguments?.getInt((CATEGORY_ID))
        return ItemCategoryView(getMvpActivity(), ItemCategoryView.ViewCreator(getMvpActivity(), null), tabId, categoryId)
    }

}