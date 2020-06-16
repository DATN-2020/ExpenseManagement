package datn.datn_expansemanagement.screen.overview.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.overview.presentation.model.OverviewExchangeRateViewModel

class OverviewMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(OverviewExchangeRateViewModel())

//        val listBarEntry = mutableListOf<BarEntry>()
//        listBarEntry.add(BarEntry(945f, 0f))
//        listBarEntry.add(BarEntry(945f, 0f))
//        listBarEntry.add(BarEntry(1040f, 1f))
//        listBarEntry.add(BarEntry(1133f, 2f))
//        listBarEntry.add(BarEntry(1240f, 3f))
//        listBarEntry.add(BarEntry(1369f, 4f))
//        listBarEntry.add(BarEntry(1487f, 5f))
//        listBarEntry.add(BarEntry(1600f, 6f))
//        listBarEntry.add(BarEntry(1700f, 7f))
//        val listYear = mutableListOf<String>()
//        listYear.add("2008")
//        listYear.add("2009")
//        listYear.add("2010")
//        listYear.add("2011")
//        listYear.add("2012")
//        listYear.add("2013")
//        listYear.add("2014")
//        listYear.add("2015")
//        listYear.add("2016")
//
//        listReturn.add(
//            TestChart(
//                listBarEntry = listBarEntry,
//                listYear = listYear
//            )
//        )

        return listReturn
    }

}