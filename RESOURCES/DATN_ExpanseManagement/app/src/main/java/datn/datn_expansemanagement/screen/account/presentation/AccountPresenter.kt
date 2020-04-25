package datn.datn_expansemanagement.screen.account.presentation

import datn.datn_expansemanagement.screen.account.domain.AccountMapper

class AccountPresenter:AccountContract.Presenter(){
    override fun getData() {
        view?.showData(AccountMapper().map(""))
    }

}