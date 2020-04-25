package datn.datn_expansemanagement.screen.account.presentation

import datn.datn_expansemanagement.screen.account.domain.AccountMapper

class AccountPresenter:AccountContract.Presenter(){
    private val mResource = AccountResource()
    override fun getData() {
        view?.showData(AccountMapper(mResource).map(""))
    }

}