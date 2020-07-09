package datn.datn_expansemanagement.screen.report.presentation

import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity

class ReportResource(val mvpActivity: MvpActivity) : AndroidResourceProvider() {
    fun getTextReportFinance(): String {
        return resourceManager.getString(R.string.text_report_finance)
    }

    fun getTextReportReceive(): String {
        return resourceManager.getString(R.string.text_report_receive)
    }

    fun getTextReportDonate(): String {
        return resourceManager.getString(R.string.text_report_donate)
    }

    fun getTextReportFriend(): String {
        return resourceManager.getString(R.string.text_report_friend)
    }

    fun getTextReportLoan(): String {
        return resourceManager.getString(R.string.text_report_loan)
    }

    fun getTextReportTrip(): String {
        return resourceManager.getString(R.string.text_report_trip)
    }

    fun getTextTitle(): String {
        return resourceManager.getString(R.string.text_title_report)
    }

    fun getColorChart(): Int {
        return resourceManager.getColor(R.color.color_219dfd)
    }

    fun getColorChart4(): Int {
        return resourceManager.getColor(R.color.color_f69524)
    }

    fun getColorChart5(): Int {
        return resourceManager.getColor(R.color.color_9eabbe)
    }

    fun getColorDonate(): Int {
        return resourceManager.getColor(R.color.color_ee403f)
    }

    fun getTextChartColor(): Int {
        return resourceManager.getColor(R.color.color_399b54)
    }

    fun getTextLabelChart(): Int {
        return resourceManager.getColor(R.color.black)
    }


    fun getBackgroundChart(): Int {
        return resourceManager.getColor(R.color.white)
    }

    fun getTypeFaceMedium(): Typeface? {
        return ResourcesCompat.getFont(mvpActivity, R.font.roboto_medium)
    }

    fun getTypeFaceRegular(): Typeface? {
        return ResourcesCompat.getFont(mvpActivity, R.font.roboto_regular)
    }
}