package datn.datn_expansemanagement.screen.report.domain

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.ReportResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.report.presentation.model.*
import kotlin.random.Random

class ReportDefaultMapper : Mapper<ReportResponse, MutableList<ViewModel>> {
    override fun map(input: ReportResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val list = ArrayList<BarEntry>()
        if(input.data != null){
            listReturn.add(
                ReportHeaderItemViewModel(
                    beginBalance = input.data.beginBalance.getValueOrDefaultIsZero(),
                    endBalance = input.data.endBalance.getValueOrDefaultIsZero()
                )
            )
            listReturn.add(
                ReportNetIncomeViewModel(
                    priceNetIncome = input.data.endBalance.getValueOrDefaultIsZero()
                )
            )

            list.add(BarEntry(1.toFloat(), input.data.totalIncomeOutcome1.toFloat()))
            list.add(BarEntry(2.toFloat(), input.data.totalIncomeOutcome2.toFloat()))
            list.add(BarEntry(3.toFloat(), input.data.totalIncomeOutcome3.toFloat()))
            list.add(BarEntry(4.toFloat(), input.data.totalIncomeOutcome4.toFloat()))
            list.add(BarEntry(5.toFloat(), input.data.totalIncomeOutcome5.toFloat()))
            list.add(BarEntry(6.toFloat(), input.data.totalIncomeOutcome6.toFloat()))
            list.add(BarEntry(7.toFloat(), input.data.totalIncomeOutcome7.toFloat()))
            list.add(BarEntry(8.toFloat(), input.data.totalIncomeOutcome8.toFloat()))
            list.add(BarEntry(9.toFloat(), input.data.totalIncomeOutcome9.toFloat()))
            list.add(BarEntry(10.toFloat(), input.data.totalIncomeOutcome10.toFloat()))
            list.add(BarEntry(11.toFloat(), input.data.totalIncomeOutcome11.toFloat()))
            list.add(BarEntry(12.toFloat(), input.data.totalIncomeOutcome12.toFloat()))

            listReturn.add(ReportBarChartViewModel(list))

            listReturn.add(
                ReportBalanceViewModel(
                    priceReceive = input.data.totalIncome,
                    priceDonate = input.data.totalOutcome
                )
            )

            val listPieEntry = ArrayList<PieEntry>()
            if(input.data.totalIncome != 0.0){
                listPieEntry.add(PieEntry((input.data.totalIncome / (input.data.totalIncome + input.data.totalOutcome) * 100).toFloat(), "Khoản thu"))

            }
            if(input.data.totalOutcome != 0.0){
                listPieEntry.add(PieEntry(100 - (input.data.totalIncome / (input.data.totalIncome + input.data.totalOutcome) * 100).toFloat(), "Khoản chi"))

            }
            listReturn.add(ReportPieChartViewModel(listPieEntry))

            listReturn.add(
                ReportBottomItemViewModel(
                    priceDue = input.data.totalLoan,
                    priceBorrow = input.data.totalBorrow,
                    priceOther = input.data.totalOther
                )
            )
        }

        return listReturn
    }

}