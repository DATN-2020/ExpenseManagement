package datn.datn_expansemanagement.screen.control_wallet

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.ControlWalletView

class ControlWalletActivity : MvpActivity() {
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val data = intent?.getParcelableExtra<WalletViewModel>(WalletViewModel::class.java.simpleName)
        val isOtherWallet : Boolean? = intent?.getBooleanExtra("isOtherWallet", false)
        return ControlWalletView(this, ControlWalletView.ViewCreator(this, null), data, isOtherWallet)
    }

}