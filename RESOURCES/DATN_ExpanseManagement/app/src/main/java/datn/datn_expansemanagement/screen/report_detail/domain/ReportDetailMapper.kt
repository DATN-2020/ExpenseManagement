package datn.datn_expansemanagement.screen.report_detail.domain

import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailDayViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailHeaderViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailItemViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailPieChartViewModel


class ReportDetailMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
//        list.add(
//            ReportBalanceViewModel(
//                priceReceive = 3000000.0,
//                priceDonate = 2000000.0
//            )
//        )

        list.add(
            ReportDetailHeaderViewModel(
                priceReceive = 3000000.0,
                priceDonate = 2000000.0
            )
        )

        val listPieEntry = ArrayList<PieEntry>()
        listPieEntry.add(PieEntry(Math.random().toFloat(), "Ăn uống"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "Đi lại"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "Đầu tư"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "Hiếu hỉ"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "ABC"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "ABDS"))
        listPieEntry.add(PieEntry(Math.random().toFloat(), "XYZ"))

        val listSelected = ArrayList<PieEntry>()
        var totalValueOther = 0.0

        listPieEntry.sortByDescending { t-> t.value }

        listPieEntry.forEachIndexed { index, pieEntry ->
            if(index >= 4){
                totalValueOther += pieEntry.value
                pieEntry.label = "Khác"
            }else{
                listSelected.add(pieEntry)
            }
        }

        listSelected.add(PieEntry(totalValueOther.toFloat(), "Khác"))
        list.add(ReportDetailPieChartViewModel(list = listSelected))

        list.add(ReportDetailDayViewModel(
            numberDay = 9,
            dayOfWeek = "Hôm nay",
            month = "9/2020",
            inCome = 3000000.0,
            outCome = 150000.0
        ))

        list.add(ReportDetailItemViewModel(
            name = "Ăn uống",
            price = 120000.0
        ))
        list.add(ReportDetailItemViewModel(
            name = "Ăn uống",
            price = 120000.0
        ))
        list.add(ReportDetailItemViewModel(
            name = "Ăn uống",
            price = 120000.0,
            isLast = true
        ))

        list.add(ReportDetailDayViewModel(
            numberDay = 9,
            dayOfWeek = "Hôm nay",
            month = "9/2020",
            inCome = 3000000.0,
            outCome = 150000.0
        ))
        list.add(ReportDetailItemViewModel(
            name = "Ăn uống",
            price = 120000.0
        ))
        list.add(ReportDetailItemViewModel(
            name = "Ăn uống",
            price = 120000.0,
            isLast = true
        ))

        return list
    }

}