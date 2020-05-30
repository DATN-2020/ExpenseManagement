package datn.datn_expansemanagement.screen.contacts.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.contacts.domain.ContractsMapper

class ContactsPresenter(private val mvpActivity: MvpActivity) : ContactsContract.Presenter() {
    override fun getData() {
        view?.showData(ContractsMapper(mvpActivity).map(""))
    }
}