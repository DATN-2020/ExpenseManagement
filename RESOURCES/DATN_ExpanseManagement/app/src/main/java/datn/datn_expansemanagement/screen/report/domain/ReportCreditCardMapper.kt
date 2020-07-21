package datn.datn_expansemanagement.screen.report.domain

import com.github.mikephil.charting.data.BarEntry
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
import kotlin.random.Random

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
                date = data.startDate.getValueOrDefaultIsEmpty(),
                isFinish = data.isFinish
            )
        )
        listReturn.add(
            ReportProcessCardViewModel(
                currentPrice = (
                        ((dateToDays(convertStringToDate(getCurrentDate())!!)).toDouble() - dateToDays(
                            convertStringToDate(data.startDate!!)!!
                        ).toDouble()) / 365 * data.interest!!),
                endDate = data.endDate.getValueOrDefaultIsEmpty(),
                progress = (dateToDays(convertStringToDate(getCurrentDate())!!) * 100) / dateToDays(
                    convertStringToDate(data.endDate!!)!!
                )
            )
        )

        if (!input.data.isNullOrEmpty() && data != null) {
            input.data.forEach {
                if(it.isIncome){
                    totalIncome += it.priceTrans
                }else{
                    totalOutcome += it.priceTrans
                }
            }

            list.add(PieEntry(totalIncome.toFloat(), "Nhập vào"))
            list.add(PieEntry(totalOutcome.toFloat(), "Rút ra"))
            listReturn.add(ReportPieChartViewModel(list))
        }else{
            listReturn.add(ReportPieChartViewModel(list))
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