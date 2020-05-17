package datn.datn_expansemanagement.screen.category.presentation

import datn.datn_expansemanagement.screen.category.domain.CategoryMapper

class CategoryPresenter(val mResource: CategoryResource) : CategoryContract.Presenter(){
    override fun getData() {
        view?.showData(CategoryMapper(mResource).map(""))
    }

}