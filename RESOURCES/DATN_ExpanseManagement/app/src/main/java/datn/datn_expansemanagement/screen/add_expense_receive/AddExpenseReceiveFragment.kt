package datn.datn_expansemanagement.screen.add_expense_receive

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.AddExpenseReceiveView

class AddExpenseReceiveFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return AddExpenseReceiveView(getMvpActivity(), AddExpenseReceiveView.ViewCreator(getMvpActivity(), null))
    }

}