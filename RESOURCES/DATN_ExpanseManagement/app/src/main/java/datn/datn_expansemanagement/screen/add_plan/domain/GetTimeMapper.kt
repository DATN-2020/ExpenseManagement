package datn.datn_expansemanagement.screen.add_plan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_plan.presentation.model.TimeItemViewModel

class GetTimeMapper() : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(TimeItemViewModel(
            id = 1,
            name = "Mỗi ngày",
            value = "8/7/2020 - 9/7/2020"
        ))
        list.add(TimeItemViewModel(
            id = 2,
            name = "Mỗi tháng",
            value = "8/7/2020 - 8/8/2020",
            isLast = true
        ))
        return list
    }

}