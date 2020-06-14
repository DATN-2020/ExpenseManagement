package datn.datn_expansemanagement.screen.add_expense_loan

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.AddExpenseLoanView

class AddExpenseLoanFragment: MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return AddExpenseLoanView(getMvpActivity(), AddExpenseLoanView.ViewCreator(getMvpActivity(), null))
    }
}