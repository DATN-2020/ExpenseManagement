package datn.datn_expansemanagement.screen.report_detail.main.presentation

import datn.datn_expansemanagement.screen.report_detail.domain.ReportDetailMapper

class ReportDetailPresenter : ReportDetailContract.Presenter(){
    override fun getData() {
        view?.showData(ReportDetailMapper().map(""))
    }

}