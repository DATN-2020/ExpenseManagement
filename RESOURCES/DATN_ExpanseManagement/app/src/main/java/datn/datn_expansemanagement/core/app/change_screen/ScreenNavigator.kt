package datn.datn_expansemanagement.core.app.change_screen

import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateCategoryViewModel

interface ScreenNavigator {
    fun gotoCategoryActivity(categoryId: Int? = null)
    fun gotoAddCategoryActivity()
}