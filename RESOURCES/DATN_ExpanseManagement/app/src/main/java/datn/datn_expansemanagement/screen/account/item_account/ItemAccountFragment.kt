package datn.datn_expansemanagement.screen.account.item_account

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.account.item_account.presentation.ItemAccountView
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel

class ItemAccountFragment : MvpFragment() {

    companion object {
        private const val KEY_TAB = "KEY_TAB"

        fun newInstance(data: ViewModel): ItemAccountFragment {
            val recipeTabFragment = ItemAccountFragment()
            val bundle = Bundle()
            if(data is TabItemViewModel){
                bundle.putInt(KEY_TAB, data.id)
            }
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val tabId = arguments?.getInt(KEY_TAB)
            return ItemAccountView(
                getMvpActivity(),
                ItemAccountView.ViewCreator(getMvpActivity(), null), tabId
            )
    }

}