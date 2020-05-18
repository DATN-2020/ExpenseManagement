package datn.datn_expansemanagement.screen.list_type_category.presentation

import datn.datn_expansemanagement.screen.list_type_category.domain.ListTypeCategoryMapper

class ListTypeCategoryPresenter : ListTypeCategoryContract.Presenter(){
    override fun getData(typeId: Int?) {
        view?.showData(ListTypeCategoryMapper(typeId).map(""))
    }

}