package datn.datn_expansemanagement.screen.list_wallet

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.list_wallet.presentation.ListWalletView

class ListWalletActivity : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ListWalletView(this, ListWalletView.ViewCreator(this , null))
    }

}
