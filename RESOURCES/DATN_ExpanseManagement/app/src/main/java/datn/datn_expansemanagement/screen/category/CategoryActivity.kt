package datn.datn_expansemanagement.screen.category

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.category.presentation.CategoryView

class CategoryActivity : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        val categoryId = intent?.getIntExtra(AddExpenseCategoryViewModel::class.java.simpleName, -1)
        return CategoryView(this, CategoryView.ViewCreator(this, null), categoryId)
    }
}