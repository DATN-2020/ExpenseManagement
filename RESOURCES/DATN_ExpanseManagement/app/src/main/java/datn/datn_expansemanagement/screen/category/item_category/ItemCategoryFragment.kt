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

        fun newInstance(data: ViewModel): ItemCategoryFragment {
            val recipeTabFragment = ItemCategoryFragment()
            val bundle = Bundle()
            if(data is TabItemViewModel){
                bundle.putString(KEY_TAB, data.name)
            }
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val tabName = arguments?.getString(KEY_TAB)
        return ItemCategoryView(getMvpActivity(), ItemCategoryView.ViewCreator(getMvpActivity(), null), tabName)
    }

}