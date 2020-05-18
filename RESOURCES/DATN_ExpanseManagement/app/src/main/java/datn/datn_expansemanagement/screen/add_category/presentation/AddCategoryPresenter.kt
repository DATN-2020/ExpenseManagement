package datn.datn_expansemanagement.screen.add_category.presentation

import datn.datn_expansemanagement.screen.add_category.domain.AddCategoryMapper

class AddCategoryPresenter : AddCategoryContract.Presenter(){
    override fun getData() {
        view?.showData(AddCategoryMapper().map(""))
    }

}