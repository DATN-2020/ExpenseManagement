package datn.datn_expansemanagement.screen.report.presentation

import datn.datn_expansemanagement.screen.report.domain.ReportMapper

class ReportPresenter : ReportContract.Presenter(){
    private val mResource = ReportResource()
    override fun getData() {
        view?.showData(ReportMapper(mResource).map(""))
    }

}