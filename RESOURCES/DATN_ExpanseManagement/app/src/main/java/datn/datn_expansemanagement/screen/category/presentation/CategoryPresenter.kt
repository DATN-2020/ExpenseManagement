package datn.datn_expansemanagement.screen.category.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.category.domain.CategoryMapper

class CategoryPresenter(val mResource: CategoryResource, private val screenNavigator: AndroidScreenNavigator) : CategoryContract.Presenter(){
    override fun getData() {
        view?.showData(CategoryMapper(mResource).map(""))
    }

    override fun gotoAddCategoryActivity() {
        screenNavigator.gotoAddCategoryActivity()
    }
}