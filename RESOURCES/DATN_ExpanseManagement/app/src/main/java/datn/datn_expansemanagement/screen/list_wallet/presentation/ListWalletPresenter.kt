package datn.datn_expansemanagement.screen.list_wallet.presentation

import datn.datn_expansemanagement.screen.list_wallet.domain.ListWalletMapper

class ListWalletPresenter : ListWalletContract.Presenter(){
    override fun getData(walletId: Int?) {
        view?.showData(ListWalletMapper(walletId).map(""))
    }

    override fun gotoCreateWalletActivity() {

    }

}