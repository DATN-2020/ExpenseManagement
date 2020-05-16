package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import datn.datn_expansemanagement.screen.add_expense_donate.domain.AddExpenseDonateMapper

class AddExpenseDonatePresenter : AddExpenseDonateContract.Presenter(){
    override fun getData() {
        view?.showData(AddExpenseDonateMapper().map(""))
    }

}