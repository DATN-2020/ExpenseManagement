package datn.datn_expansemanagement.screen.history.presentation

import datn.datn_expansemanagement.screen.history.domain.HistoryMapper

class HistoryPresenter : HistoryContract.Presenter(){
    override fun getData() {
        view?.showData(HistoryMapper().map(""))
    }
}