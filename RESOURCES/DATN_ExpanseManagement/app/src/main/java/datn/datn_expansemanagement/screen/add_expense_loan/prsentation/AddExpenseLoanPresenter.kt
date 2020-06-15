package datn.datn_expansemanagement.screen.add_expense_loan.prsentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.add_expense_loan.domain.AddExpenseLoanMapper

class AddExpenseLoanPresenter(private val screenNavigator: AndroidScreenNavigator): AddExpenseLoanContract.Presenter(){
    override fun getData(isDonate: Boolean) {
        view?.showData(AddExpenseLoanMapper(isDonate).map(""))
    }

    override fun gotoChooseWalletActivity(walletId: Int?) {
        screenNavigator.gotoChooseWalletActivity(walletId)
    }

    override fun gotoChooseTripActivity() {
        screenNavigator.gotoChooseTripActivity()
    }

    override fun gotoChooseFriend() {
        screenNavigator.gotoChooseFriendActivity()
    }

    override fun gotoLocationActivity() {

    }
}