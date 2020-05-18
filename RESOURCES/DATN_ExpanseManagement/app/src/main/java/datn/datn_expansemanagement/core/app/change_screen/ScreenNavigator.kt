package datn.datn_expansemanagement.core.app.change_screen

import datn.datn_expansemanagement.screen.add_category.presentation.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateCategoryViewModel

interface ScreenNavigator {
    fun gotoCategoryActivity(categoryId: Int? = null)
    fun gotoListTypeCategory(data: TypeCategoryDataIntent? = null)
    fun gotoAddCategoryActivity()
}