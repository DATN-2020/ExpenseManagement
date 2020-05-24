package datn.datn_expansemanagement.screen.trip.item_trip.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.trip.item_trip.presentation.model.ItemTripViewModel

class ItemTripMapper(private val isFinished: Boolean?) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val listReturn = mutableListOf<ViewModel>()
        for (i in 1..3){
            list.add(ItemTripViewModel(
                id = i,
                name = "Trip Vũng Tàu",
                isFinished = false,
                nameIcon = "HP"
            ))
        }

        for (i in 1..3){
            list.add(ItemTripViewModel(
                id = i,
                name = "Trip Đà Lạt",
                isFinished = true,
                nameIcon = "HP"
            ))
        }

        list.forEach {
            if(it is ItemTripViewModel){
                if(it.isFinished  == isFinished){
                    listReturn.add(it)
                }
            }
        }
        return listReturn
    }

}