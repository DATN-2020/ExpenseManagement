package datn.datn_expansemanagement.screen.report.domain

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.report.presentation.model.*
import kotlin.random.Random

class ReportCreditCardMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val list = ArrayList<BarEntry>()
        listReturn.add(
            ReportHeaderCardViewModel(
                price = 1000000000.0,
                date = "10/7/2020"
            )
        )
        listReturn.add(
            ReportProcessCardViewModel(
                totalPrice = 1000000000.0,
                currentPrice = 10000000.0
            )
        )

        for (i in 1..12) {
            val random = Random.nextInt(1, 10)
            list.add(BarEntry(i.toFloat(), random.toFloat() * 10))
        }
        listReturn.add(ReportBarChartViewModel(list))

        listReturn.add(
            ReportBottomCardViewModel(
                priceReceive = 10000000.0,
                priceDonate = 0.0
            )
        )

//        val listPieEntry = ArrayList<PieEntry>()
//        listPieEntry.add(PieEntry(Math.random().toFloat(), "Khoản thu"))
//        listPieEntry.add(PieEntry(Math.random().toFloat(), "Khoản chi"))
//        listReturn.add(ReportPieChartViewModel(listPieEntry))

//        listReturn.add(
//            ReportBottomItemViewModel(
//                priceDue = 3000000.0,
//                priceBorrow = 2000000.0,
//                priceOther = 0.0
//            )
//        )

        return listReturn
    }

}