package datn.datn_expansemanagement.screen.report_detail.domain

import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.ReportDetailResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailHeaderViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailItemViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailPieChartViewModel
import java.text.SimpleDateFormat


class ReportDetailMapper : Mapper<ReportDetailResponse, MutableList<ViewModel>> {
    override fun map(input: ReportDetailResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val listPieEntry = ArrayList<PieEntry>()
        var totalIncome = 0.0
        var totalOutcome = 0.0
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                if (it.isCome) {
                    totalIncome += it.amount
                } else {
                    totalOutcome += it.amount
                }
                listPieEntry.add(PieEntry(it.amount.toFloat(), it.desciption))
            }
        }

        list.add(
            ReportDetailHeaderViewModel(
                priceDonate = totalOutcome,
                priceReceive = totalIncome
            )
        )

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

        if (listPieEntry.size >= 5) {
            listSelected.add(PieEntry(totalValueOther.toFloat(), "Khác"))
        }
        list.add(ReportDetailPieChartViewModel(list = listSelected))

        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                list.add(
                    ReportDetailItemViewModel(
                        name = it.name.getValueOrDefaultIsEmpty(),
                        price = it.amount.getValueOrDefaultIsZero(),
                        imgUrl = it.image.getValueOrDefaultIsEmpty(),
                        date = Utils.convertDateFormat(
                            it.dateCome.getValueOrDefaultIsEmpty(),
                            SimpleDateFormat("yyyy-MM-dd"),
                            SimpleDateFormat("dd/MM/yyyy")
                        ),
                        des = it.desciption.getValueOrDefaultIsEmpty()
                    )
                )
            }
            (list.last() as ReportDetailItemViewModel).isLast = true
        }
        return list
    }

}