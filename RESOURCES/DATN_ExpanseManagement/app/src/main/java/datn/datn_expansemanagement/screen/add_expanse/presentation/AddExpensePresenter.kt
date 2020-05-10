package datn.datn_expansemanagement.screen.add_expanse.presentation

import datn.datn_expansemanagement.screen.add_expanse.domain.AddExpenseMapper

class AddExpensePresenter : AddExpenseContract.Presenter(){
    override fun getData() {
        view?.showData(AddExpenseMapper().map(""))
    }
}