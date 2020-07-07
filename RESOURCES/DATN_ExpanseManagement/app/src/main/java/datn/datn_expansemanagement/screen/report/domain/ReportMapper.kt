package datn.datn_expansemanagement.screen.report.domain

import com.github.mikephil.charting.data.BarEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.report.presentation.ReportResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBarChartViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import kotlin.random.Random

class ReportMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val list = ArrayList<BarEntry>()
        for (i in 1..12) {
            val random = Random.nextInt(-10, 10)
            list.add(BarEntry(i.toFloat(), random.toFloat() * 10))
        }
        listReturn.add(ReportBarChartViewModel(list))
        return listReturn
    }

}