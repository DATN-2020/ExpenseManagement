package datn.datn_expansemanagement.screen.add_expense_donate

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateView

class AddExpenseDonateFragment(private val isDonate: Boolean = false) :MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return AddExpenseDonateView(getMvpActivity(), AddExpenseDonateView.ViewCreator(getMvpActivity(), null), isDonate)
    }
}