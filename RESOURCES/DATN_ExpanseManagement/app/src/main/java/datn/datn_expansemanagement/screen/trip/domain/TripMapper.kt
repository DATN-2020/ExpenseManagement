package datn.datn_expansemanagement.screen.trip.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TripResponse
import datn.datn_expansemanagement.screen.trip.presentation.model.TripItemViewModel

class TripMapper : Mapper<TripResponse, MutableList<ViewModel>> {
    override fun map(input: TripResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.data.isNullOrEmpty()){
            input.data.forEach {
                list.add(
                    TripItemViewModel(
                        id = it.idTrip,
                        name = it.nameTrip
                    )
                )
            }

        }
        return list
    }

}