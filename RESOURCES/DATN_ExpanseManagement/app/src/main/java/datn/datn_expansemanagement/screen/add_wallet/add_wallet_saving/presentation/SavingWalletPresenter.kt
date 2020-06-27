package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain.SavingWalletMapper

class SavingWalletPresenter : SavingWalletContact.Presenter(){
    override fun getData() {
        view?.showData(SavingWalletMapper().map(""))
    }

}