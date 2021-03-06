package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.ItemTabBudgetView

class ItemTabBudgetFragment : MvpFragment() {

    companion object {
        private const val KEY_TAB = "KEY_TAB"
        private const val ID_WALLET = "ID_WALLET"

        fun newInstance(data: ViewModel, idWallet: Int): ItemTabBudgetFragment {
            val recipeTabFragment = ItemTabBudgetFragment()
            val bundle = Bundle()
            if(data is TabItemViewModel){
                bundle.putParcelable(KEY_TAB, data)
            }
            bundle.putInt(ID_WALLET, idWallet)
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val dataType = arguments?.getParcelable<TabItemViewModel>(KEY_TAB)
        val idWallet = arguments?.getInt(ID_WALLET)
        return ItemTabBudgetView(
            getMvpActivity(),
            ItemTabBudgetView.ViewCreator(getMvpActivity(), null),
            dataType,
            idWallet!!
        )
    }

}