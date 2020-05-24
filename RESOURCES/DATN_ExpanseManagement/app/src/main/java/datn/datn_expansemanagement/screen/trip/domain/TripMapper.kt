package datn.datn_expansemanagement.screen.trip.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.trip.presentation.model.TripItemViewModel

class TripMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(
            TripItemViewModel(
                isFinished = false,
                name = "ĐANG DIỄN RA"
            )
        )
        list.add(
            TripItemViewModel(
                isFinished = true,
                name = "ĐÃ KẾT THÚC"
            )
        )
        return list
    }

}