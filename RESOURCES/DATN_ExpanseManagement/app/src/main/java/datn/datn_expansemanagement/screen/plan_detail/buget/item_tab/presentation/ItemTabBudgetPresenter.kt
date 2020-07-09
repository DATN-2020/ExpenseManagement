package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain.ItemTabBudgetMapper

class ItemTabBudgetPresenter : ItemTabBudgetContract.Presenter(){
    override fun getData(tab: TabItemViewModel) {
        view?.showData(ItemTabBudgetMapper(tab).map(""))
    }

}