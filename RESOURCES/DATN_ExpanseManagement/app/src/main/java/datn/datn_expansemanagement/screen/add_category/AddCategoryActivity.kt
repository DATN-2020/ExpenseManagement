package datn.datn_expansemanagement.screen.add_category

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_category.presentation.AddCategoryView

class AddCategoryActivity : MvpActivity() {
    override fun createAndroidMvpView(): AndroidMvpView {
        return AddCategoryView(this, AddCategoryView.ViewCreator(this, null))
    }

}
