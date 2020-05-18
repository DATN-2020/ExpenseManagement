package datn.datn_expansemanagement.screen.list_type_category

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_category.presentation.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.list_type_category.presentation.ListTypeCategoryView

class ListTypeCategory : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        val data = intent.getParcelableExtra<TypeCategoryDataIntent>(TypeCategoryDataIntent::class.java.simpleName)
        return ListTypeCategoryView(this, ListTypeCategoryView.ViewCreator(this, null), data)
    }

}
