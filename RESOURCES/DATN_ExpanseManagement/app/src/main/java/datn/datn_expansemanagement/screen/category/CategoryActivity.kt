package datn.datn_expansemanagement.screen.category

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.category.presentation.CategoryView

class CategoryActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        return CategoryView(this, CategoryView.ViewCreator(this, null))
    }
}