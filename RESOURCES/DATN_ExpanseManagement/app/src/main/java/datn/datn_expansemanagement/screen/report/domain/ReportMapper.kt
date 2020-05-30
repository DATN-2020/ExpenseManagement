package datn.datn_expansemanagement.screen.report.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.report.presentation.ReportResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel

class ReportMapper(private val mResource: ReportResource) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(ReportViewModel(
            type = ReportViewModel.TypeReport.RECEIVE,
            name = mResource.getTextReportReceive()
        ))
        listReturn.add(ReportViewModel(
            type = ReportViewModel.TypeReport.DONATE,
            name = mResource.getTextReportDonate()
        ))
        listReturn.add(ReportViewModel(
            type = ReportViewModel.TypeReport.FINANCE,
            name = mResource.getTextReportFinance()
        ))
        listReturn.add(ReportViewModel(
            type = ReportViewModel.TypeReport.LOAN,
            name = mResource.getTextReportLoan()
        ))
        listReturn.add(ReportViewModel(
            type = ReportViewModel.TypeReport.FRIEND,
            name = mResource.getTextReportFriend()
        ))
        listReturn.add(ReportViewModel(
            type = ReportViewModel.TypeReport.TRIP,
            name = mResource.getTextReportTrip()
        ))
        return listReturn
    }

}