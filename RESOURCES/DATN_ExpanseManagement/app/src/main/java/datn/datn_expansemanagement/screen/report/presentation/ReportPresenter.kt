package datn.datn_expansemanagement.screen.report.presentation

import datn.datn_expansemanagement.screen.report.domain.ReportMapper

class ReportPresenter : ReportContract.Presenter(){
    override fun getData() {
        view?.showData(ReportMapper().map(""))
    }

}