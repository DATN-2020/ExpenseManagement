package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.domain.DefaultWalletMapper

class DefaultWalletPresenter : DefaultWalletContract.Presenter() {
    override fun getData() {
        view?.showData(DefaultWalletMapper().map(""))
    }
}