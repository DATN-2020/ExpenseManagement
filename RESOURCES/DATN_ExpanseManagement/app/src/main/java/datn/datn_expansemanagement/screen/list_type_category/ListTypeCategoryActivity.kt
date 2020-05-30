package datn.datn_expansemanagement.screen.list_type_category

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.list_type_category.presentation.ListTypeCategoryView

class ListTypeCategoryActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val data = intent.getParcelableExtra<TypeCategoryDataIntent>(TypeCategoryDataIntent::class.java.simpleName)
        return ListTypeCategoryView(this, ListTypeCategoryView.ViewCreator(this, null), data)
    }

}
