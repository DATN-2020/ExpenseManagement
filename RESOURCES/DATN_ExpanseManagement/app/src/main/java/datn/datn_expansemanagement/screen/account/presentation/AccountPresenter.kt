package datn.datn_expansemanagement.screen.account.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.account.domain.AccountMapper

class AccountPresenter(private val screenNavigator: AndroidScreenNavigator):AccountContract.Presenter(){
    private val mResource = AccountResource()
    override fun getData() {
        view?.showData(AccountMapper(mResource).map(""))
    }

    override fun gotoAddWalletActivity(type: Int) {
        screenNavigator.gotoAddWalletActivity(type)
    }

}