package datn.datn_expansemanagement.screen.overview.presentation

import datn.datn_expansemanagement.screen.overview.domain.OverviewMapper

class OverviewPresenter : OverviewContract.Presenter(){
    override fun getData() {
        view?.showData(OverviewMapper().map(""))
    }
}