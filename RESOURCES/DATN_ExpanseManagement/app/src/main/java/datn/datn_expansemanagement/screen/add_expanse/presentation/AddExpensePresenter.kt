package datn.datn_expansemanagement.screen.add_expanse.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.add_expanse.domain.AddExpenseMapper

class AddExpensePresenter(private val screenNavigator: AndroidScreenNavigator) : AddExpenseContract.Presenter(){
    override fun getData() {
        view?.showData(AddExpenseMapper().map(""))
    }

    override fun gotoHistoryActivity() {
        screenNavigator.gotoHistoryActivity()
    }
}