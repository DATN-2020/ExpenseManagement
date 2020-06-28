package datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.presentation

import datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.domain.AccumulationWalletMapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource

class AccumulationWalletPresenter : AccumulationWalletContract.Presenter() {
    private val mResource = AddWalletResource()
    override fun getData() {
        view?.showData(AccumulationWalletMapper(mResource).map(""))
    }
}