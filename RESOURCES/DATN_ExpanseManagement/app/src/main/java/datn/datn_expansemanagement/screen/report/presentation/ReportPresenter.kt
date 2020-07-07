package datn.datn_expansemanagement.screen.report.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.report.domain.ReportMapper
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel

class ReportPresenter(private val screenNavigator: AndroidScreenNavigator): ReportContract.Presenter(){
    override fun getData() {
        view?.showData(ReportMapper().map(""))
    }

    override fun gotoReportDetailActivity(data: ReportViewModel) {
        screenNavigator.gotoReportDetailActivity(data)
    }

}