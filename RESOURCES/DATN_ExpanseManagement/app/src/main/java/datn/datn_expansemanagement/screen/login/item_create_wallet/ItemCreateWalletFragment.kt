package datn.datn_expansemanagement.screen.login.item_create_wallet

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.login.item_create_wallet.presentation.ItemCreateWalletView
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent

class ItemCreateWalletFragment : MvpFragment(){

    companion object {
        private const val DATA = "DATA"

        fun newInstance(data: ViewModel): ItemCreateWalletFragment {
            val recipeTabFragment = ItemCreateWalletFragment()
            val bundle = Bundle()
            if(data is PassportDataIntent){
                bundle.putParcelable(DATA, data)
            }
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val data = arguments?.getParcelable<PassportDataIntent>(DATA)
        return ItemCreateWalletView(getMvpActivity(), ItemCreateWalletView.ViewCreator(getMvpActivity(), null), data)
    }

}