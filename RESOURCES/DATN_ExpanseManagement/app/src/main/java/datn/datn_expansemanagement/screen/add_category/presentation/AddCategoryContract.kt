package datn.datn_expansemanagement.screen.add_category.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent

interface AddCategoryContract {
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun gotoListTypeCategoryActivity(data: TypeCategoryDataIntent? = null)
    }
}