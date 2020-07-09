package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain.ItemTabBudgetMapper

class ItemTabBudgetPresenter : ItemTabBudgetContract.Presenter(){
    override fun getData() {
        view?.showData(ItemTabBudgetMapper().map(""))
    }

}