package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain.SavingWalletMapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource

class SavingWalletPresenter : SavingWalletContact.Presenter(){
    private val mResource  = AddWalletResource()
    override fun getData() {
        view?.showData(SavingWalletMapper(mResource).map(""))
    }

}