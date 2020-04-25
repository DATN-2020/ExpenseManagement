package datn.datn_expansemanagement.screen.account.item_account.presentation

import datn.datn_expansemanagement.screen.account.item_account.domain.ItemAccountMapper

class ItemAccountPresenter : ItemAccountContract.Presenter(){
    override fun getData(tabId: Int) {
        view?.showData(ItemAccountMapper().map(tabId))
    }

}