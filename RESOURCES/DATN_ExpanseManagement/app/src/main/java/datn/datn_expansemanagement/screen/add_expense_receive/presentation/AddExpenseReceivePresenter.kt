package datn.datn_expansemanagement.screen.add_expense_receive.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.add_expense_receive.domain.AddExpenseReceiveMapper

class AddExpenseReceivePresenter(private val screenNavigator: AndroidScreenNavigator) : AddExpenseReceiveContract.Presenter(){
    override fun getData() {
        view?.showData(AddExpenseReceiveMapper().map(""))
    }

    override fun gotoCategoryActivity() {
        screenNavigator.gotoCategoryActivity()
    }
}