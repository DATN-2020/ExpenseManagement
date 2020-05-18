package datn.datn_expansemanagement.core.app.change_screen

import android.content.Intent
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_category.AddCategoryActivity
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateCategoryViewModel
import datn.datn_expansemanagement.screen.category.CategoryActivity

class AndroidScreenNavigator constructor(private val mvpActivity: MvpActivity) : ScreenNavigator{
    override fun gotoCategoryActivity(categoryId: Int?) {
        val intent = Intent(mvpActivity, CategoryActivity::class.java)
        intent.putExtra(AddExpenseDonateCategoryViewModel::class.java.simpleName, categoryId)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_CATEGORY)
    }

    override fun gotoAddCategoryActivity() {
        val intent = Intent(mvpActivity, AddCategoryActivity::class.java)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_ADD_CATEGORY)
    }
}