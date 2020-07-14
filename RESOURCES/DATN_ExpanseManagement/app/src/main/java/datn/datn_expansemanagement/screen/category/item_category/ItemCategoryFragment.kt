package datn.datn_expansemanagement.screen.category.item_category

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.ItemCategoryView

class ItemCategoryFragment : MvpFragment(){

    companion object {
        private const val KEY_TAB = "KEY_TAB"
        private const val ID_CATEGORY = "ID_CATEGORY"
        private const val IS_PLAN = "IS_PLAN"

        fun newInstance(data: ViewModel, idCategory: Int? = null, isPlan: Boolean? = false): ItemCategoryFragment {
            val recipeTabFragment = ItemCategoryFragment()
            val bundle = Bundle()
            if(data is TabItemViewModel){
                bundle.putString(KEY_TAB, data.name)
            }
            bundle.putInt(ID_CATEGORY, idCategory.getValueOrDefaultIsZero())
            bundle.putBoolean(IS_PLAN, isPlan.getValueOrDefault())
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val tabName = arguments?.getString(KEY_TAB)
        val idCategory = arguments?.getInt(ID_CATEGORY)
        val isPlan = arguments?.getBoolean(IS_PLAN)
        return ItemCategoryView(getMvpActivity(), ItemCategoryView.ViewCreator(getMvpActivity(), null), tabName, idCategory, isPlan)
    }

}