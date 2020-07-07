package datn.datn_expansemanagement.screen.report.domain

import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBarChartViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportPieChartViewModel
import kotlin.random.Random

class ReportMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val list = ArrayList<BarEntry>()
        for (i in 1..12) {
            val random = Random.nextInt(-10, 10)
            list.add(BarEntry(i.toFloat(), random.toFloat() * 10))
        }

        val listPieEntry = ArrayList<PieEntry>()
        listPieEntry.add(PieEntry(Math.random().toFloat(), "Khoản thu"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "Khoản chi"))
        listReturn.add(ReportBarChartViewModel(list))
        listReturn.add(ReportPieChartViewModel(listPieEntry))
        return listReturn
    }

}