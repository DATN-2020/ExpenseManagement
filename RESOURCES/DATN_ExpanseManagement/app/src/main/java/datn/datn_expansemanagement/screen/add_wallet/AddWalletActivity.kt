package datn.datn_expansemanagement.screen.add_wallet

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletView

class AddWalletActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val type = intent.getIntExtra("typeWallet", 0)
        return AddWalletView(this, AddWalletView.ViewCreator(this, null), type)
    }

}