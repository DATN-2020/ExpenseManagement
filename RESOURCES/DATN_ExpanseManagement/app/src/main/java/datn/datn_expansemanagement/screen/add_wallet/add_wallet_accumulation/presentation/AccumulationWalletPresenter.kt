package datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.presentation

import datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.domain.AccumulationWalletMapper

class AccumulationWalletPresenter : AccumulationWalletContract.Presenter() {
    override fun getData() {
        view?.showData(AccumulationWalletMapper().map(""))
    }
}