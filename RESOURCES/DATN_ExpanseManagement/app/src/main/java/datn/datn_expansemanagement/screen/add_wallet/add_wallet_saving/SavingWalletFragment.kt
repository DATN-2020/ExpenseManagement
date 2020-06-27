package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation.SavingWalletView

class SavingWalletFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return SavingWalletView(getMvpActivity(), SavingWalletView.ViewCreator(getMvpActivity(), null))
    }

}