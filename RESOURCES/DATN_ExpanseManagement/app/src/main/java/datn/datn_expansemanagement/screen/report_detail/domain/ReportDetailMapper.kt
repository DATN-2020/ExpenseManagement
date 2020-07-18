package datn.datn_expansemanagement.screen.report_detail.domain

import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.ReportDetailResponse
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailDayViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailHeaderViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailItemViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailPieChartViewModel


class ReportDetailMapper : Mapper<ReportDetailResponse, MutableList<ViewModel>>{
    override fun map(input: ReportDetailResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val listPieEntry = ArrayList<PieEntry>()
        var totalIncome = 0.0
        var totalOutcome = 0.0
        if(!input.data.isNullOrEmpty()){
           input.data.forEach {
               if(it.isCome){
                   totalIncome += it.amount
               }else{
                   totalOutcome += it.amount
               }
               listPieEntry.add(PieEntry(it.amount.toFloat(), it.descriptionCome))
           }
        }

        list.add(ReportDetailHeaderViewModel(
            priceDonate = totalOutcome,
            priceReceive = totalIncome
        ))

        val listSelected = ArrayList<PieEntry>()
        var totalValueOther = 0.0
        listPieEntry.sortByDescending { t -> t.value }

        listPieEntry.forEachIndexed { index, pieEntry ->
            if (index >= 4) {
                totalValueOther += pieEntry.value
                pieEntry.label = "Khác"
            } else {
                listSelected.add(pieEntry)
            }
        }

        if(listPieEntry.size >= 5){
            listSelected.add(PieEntry(totalValueOther.toFloat(), "Khác"))
        }
        list.add(ReportDetailPieChartViewModel(list = listSelected))

//        if(!input.data.isNullOrEmpty()){
//            input.data.forEach {
//                list.add(ReportDetailItemViewModel(
//
//                ))
//            }
//        }

//        list.add(ReportDetailDayViewModel(
//            numberDay = 9,
//            dayOfWeek = "Hôm nay",
//            month = "9/2020",
//            inCome = 3000000.0,
//            outCome = 150000.0
//        ))
//
//        list.add(ReportDetailItemViewModel(
//            name = "Ăn uống",
//            price = 120000.0
//        ))
//        list.add(ReportDetailItemViewModel(
//            name = "Ăn uống",
//            price = 120000.0
//        ))
//        list.add(ReportDetailItemViewModel(
//            name = "Ăn uống",
//            price = 120000.0,
//            isLast = true
//        ))
//
//        list.add(ReportDetailDayViewModel(
//            numberDay = 9,
//            dayOfWeek = "Hôm nay",
//            month = "9/2020",
//            inCome = 3000000.0,
//            outCome = 150000.0
//        ))
//        list.add(ReportDetailItemViewModel(
//            name = "Ăn uống",
//            price = 120000.0
//        ))
//        list.add(ReportDetailItemViewModel(
//            name = "Ăn uống",
//            price = 120000.0,
//            isLast = true
//        ))

        return list
    }

}