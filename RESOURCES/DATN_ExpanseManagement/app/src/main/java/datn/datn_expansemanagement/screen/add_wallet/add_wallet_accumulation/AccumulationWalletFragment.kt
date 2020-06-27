package datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.presentation.AccumulationWalletView

class AccumulationWalletFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return AccumulationWalletView(getMvpActivity(), AccumulationWalletView.ViewCreator(getMvpActivity(), null))
    }

}