package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation.DefaultWalletView

class DefaultWalletFragment : MvpFragment() {
    override fun createAndroidMvpView(): AndroidMvpView {
        return DefaultWalletView(
            getMvpActivity(),
            DefaultWalletView.ViewCreator(getMvpActivity(), null)
        )
    }

}