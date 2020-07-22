package datn.datn_expansemanagement.screen.report.domain

import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.common.AppConstants
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.ReportWalletSavingResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.presentation.model.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ReportCreditCardMapper(private val data: GetWalletItemViewModel) :
    Mapper<ReportWalletSavingResponse, MutableList<ViewModel>> {
    override fun map(input: ReportWalletSavingResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val list = ArrayList<PieEntry>()
        var totalIncome = 0.0
        var totalOutcome = 0.0

        listReturn.add(
            ReportHeaderCardViewModel(
                price = data.money.getValueOrDefaultIsZero(),
                startDate = data.startDate.getValueOrDefaultIsEmpty(),
                endDate = data.endDate.getValueOrDefaultIsEmpty(),
                isFinish = data.isFinish
            )
        )
        val currentDay = dateToDays(convertStringToDate(getCurrentDate())!!).toDouble()
        val endDate = dateToDays(convertStringToDate(data.endDate!!)!!).toDouble()
        val start = dateToDays(convertStringToDate(data.startDate!!)!!).toDouble()
        listReturn.add(
            ReportProcessCardViewModel(
                currentPrice = ((currentDay - start) / 365 * data.interest!! * data.money),
                endDate = data.endDate.getValueOrDefaultIsEmpty(),
                progress = (currentDay * 100 / endDate).toInt(),
                maxProcess = endDate.toInt()
            )
        )

        if (!input.data.isNullOrEmpty() && data != null) {
            input.data.forEach {
                if (it.isIncome) {
                    totalIncome += it.priceTrans
                } else {
                    totalOutcome += it.priceTrans
                }
            }

            list.add(PieEntry(totalIncome.toFloat(), "Nhập vào"))
            list.add(PieEntry(totalOutcome.toFloat(), "Rút ra"))
            listReturn.add(ReportPieChartViewModel(list, true))
        } else {
            listReturn.add(ReportPieChartViewModel(list, true))
        }

        listReturn.add(
            ReportBottomCardViewModel(
                priceReceive = totalIncome,
                priceDonate = totalOutcome
            )
        )

        return listReturn
    }

    private fun dateToDays(date: Date): Int {
        //  convert a date to an integer and back again
        val currentTime = date.time
        return (currentTime / AppConstants.MAGIC).toInt()
    }

    private fun convertStringToDate(value: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return try {
            format.parse(value)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    private fun getCurrentDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }
}