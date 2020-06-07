package datn.datn_expansemanagement.screen.history.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.history.presentation.model.HistoryDateItemViewModel
import datn.datn_expansemanagement.screen.history.presentation.model.HistoryItemViewModel
import datn.datn_expansemanagement.screen.history.presentation.model.HistoryTotalItemViewModel

class HistoryMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(HistoryTotalItemViewModel(
            totalIncome = 5000000.0,
            totalOutCome = 320000.0
        ))
        list.add(HistoryDateItemViewModel(
            dayOfWeek = "Hôm qua",
            inCome = 500000.0,
            month = "5/2020",
            numberDay = 30,
            outCome = 343200.0
        ))
        list.add(HistoryItemViewModel(
            money = 300000.0,
            title = "Ăn uống",
            isIncome = true,
            wallet = "Ví tiền mặt"
        ))
        list.add(HistoryItemViewModel(
            money = 300000.0,
            title = "Ăn uống",
            isIncome = true,
            wallet = "Thẻ tiết kiệm"
        ))
        return list
    }

}