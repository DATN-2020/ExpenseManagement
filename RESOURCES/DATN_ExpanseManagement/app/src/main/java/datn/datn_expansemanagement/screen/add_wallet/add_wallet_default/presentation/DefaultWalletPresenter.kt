package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.domain.DefaultWalletMapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource

class DefaultWalletPresenter : DefaultWalletContract.Presenter() {
    private val mResource = AddWalletResource()
    override fun getData() {
        view?.showData(DefaultWalletMapper(mResource).map(""))
    }
}