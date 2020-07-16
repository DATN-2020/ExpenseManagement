package datn.datn_expansemanagement.screen.add_plan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TimePeriodicResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_plan.presentation.model.TimeItemViewModel

class GetTimeMapper : Mapper<List<TimePeriodicResponse>, MutableList<ViewModel>>{
    override fun map(input: List<TimePeriodicResponse>): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.isNullOrEmpty()){
            input.forEach {
                list.add(
                    TimeItemViewModel(
                        id = it.idTime.getValueOrDefaultIsZero(),
                        name = it.desciption.getValueOrDefaultIsEmpty()
//                        value = "8/7/2020 - 9/7/2020"
                    )
                )
            }
        }
//        list.add(TimeItemViewModel(
//            id = 1,
//            name = "Mỗi ngày",
//            value = "8/7/2020 - 9/7/2020"
//        ))
//        list.add(TimeItemViewModel(
//            id = 2,
//            name = "Mỗi tháng",
//            value = "8/7/2020 - 8/8/2020",
//            isLast = true
//        ))
        return list
    }

}