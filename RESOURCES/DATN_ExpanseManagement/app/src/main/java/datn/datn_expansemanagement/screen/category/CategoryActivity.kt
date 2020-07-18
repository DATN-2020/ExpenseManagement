package datn.datn_expansemanagement.screen.category

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.category.presentation.CategoryView

class CategoryActivity : MvpActivity() {
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val idCate = intent?.getIntExtra("idCategory", 0)
        val isPlan = intent?.getBooleanExtra("isPlan", false)
        val isDonate = intent?.getBooleanExtra("isDonate", false)
        return CategoryView(
            this,
            CategoryView.ViewCreator(this, null),
            idCate,
            isPlan,
            isDonate
        )
    }
}