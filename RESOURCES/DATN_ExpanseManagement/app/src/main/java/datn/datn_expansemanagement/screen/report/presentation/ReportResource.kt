package datn.datn_expansemanagement.screen.report.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ReportResource : AndroidResourceProvider(){
    fun getTextReportFinance(): String{
        return resourceManager.getString(R.string.text_report_finance)
    }
    fun getTextReportReceive(): String{
        return resourceManager.getString(R.string.text_report_receive)
    }
    fun getTextReportDonate(): String{
        return resourceManager.getString(R.string.text_report_donate)
    }
    fun getTextReportFriend(): String{
        return resourceManager.getString(R.string.text_report_friend)
    }
    fun getTextReportLoan(): String{
        return resourceManager.getString(R.string.text_report_loan)
    }
    fun getTextReportTrip(): String{
        return resourceManager.getString(R.string.text_report_trip)
    }

    fun getTextTitle(): String{
        return resourceManager.getString(R.string.text_title_report)
    }
}