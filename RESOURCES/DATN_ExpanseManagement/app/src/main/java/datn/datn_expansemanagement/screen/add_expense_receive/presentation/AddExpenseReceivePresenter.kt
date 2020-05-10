package datn.datn_expansemanagement.screen.add_expense_receive.presentation

import datn.datn_expansemanagement.screen.add_expense_receive.domain.AddExpenseReceiveMapper

class AddExpenseReceivePresenter : AddExpenseReceiveContract.Presenter(){
    override fun getData() {
        view?.showData(AddExpenseReceiveMapper().map(""))
    }
}