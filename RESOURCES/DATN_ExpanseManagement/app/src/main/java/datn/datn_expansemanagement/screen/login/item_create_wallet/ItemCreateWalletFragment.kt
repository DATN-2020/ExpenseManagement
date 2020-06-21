package datn.datn_expansemanagement.screen.login.item_create_wallet

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.login.item_create_wallet.presentation.ItemCreateWalletView

class ItemCreateWalletFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ItemCreateWalletView(getMvpActivity(), ItemCreateWalletView.ViewCreator(getMvpActivity(), null))
    }

}