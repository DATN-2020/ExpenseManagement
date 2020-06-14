package datn.datn_expansemanagement.screen.add_expense_loan.prsentation

import datn.datn_expansemanagement.screen.add_expense_loan.domain.AddExpenseLoanMapper

class AddExpenseLoanPresenter: AddExpenseLoanContract.Presenter(){
    override fun getData(isDonate: Boolean) {
        view?.showData(AddExpenseLoanMapper(isDonate).map(""))
    }

}