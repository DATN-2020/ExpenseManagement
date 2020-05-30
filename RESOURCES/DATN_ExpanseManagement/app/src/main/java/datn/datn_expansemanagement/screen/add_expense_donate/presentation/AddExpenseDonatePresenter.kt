package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.add_expense_donate.domain.AddExpenseDonateMapper

class AddExpenseDonatePresenter(private val screenNavigator: AndroidScreenNavigator) : AddExpenseDonateContract.Presenter(){
    override fun getData() {
        view?.showData(AddExpenseDonateMapper().map(""))
    }

    override fun gotoCategoryActivity(categoryId: Int?) {
        screenNavigator.gotoCategoryActivity(categoryId)
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
        screenNavigator.gotoLocationActivity()
    }
}