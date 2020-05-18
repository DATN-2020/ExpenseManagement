package datn.datn_expansemanagement.screen.category.item_category.presentation

import datn.datn_expansemanagement.screen.category.item_category.domain.ItemCategoryMapper


class ItemCategoryPresenter : ItemCategoryContract.Presenter(){
    override fun getData(tabId: Int, categoryId: Int?) {
        view?.showData(ItemCategoryMapper(tabId, categoryId).map(""))
    }
}