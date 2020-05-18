package datn.datn_expansemanagement.screen.add_category.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.add_category.presentation.data.TypeCategoryDataIntent

class AddCategoryPresenter(private val screenNavigator: AndroidScreenNavigator) : AddCategoryContract.Presenter(){
    override fun gotoListTypeCategoryActivity(data: TypeCategoryDataIntent?) {
        screenNavigator.gotoListTypeCategory(data)
    }
}