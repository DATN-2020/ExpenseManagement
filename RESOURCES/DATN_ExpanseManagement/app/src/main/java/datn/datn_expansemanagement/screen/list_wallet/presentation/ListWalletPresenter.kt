package datn.datn_expansemanagement.screen.list_wallet.presentation

import datn.datn_expansemanagement.screen.list_wallet.domain.ListWalletMapper

class ListWalletPresenter : ListWalletContract.Presenter(){
    override fun getData() {
        view?.showData(ListWalletMapper().map(""))
    }

    override fun gotoCreateWalletActivity() {
    }

}