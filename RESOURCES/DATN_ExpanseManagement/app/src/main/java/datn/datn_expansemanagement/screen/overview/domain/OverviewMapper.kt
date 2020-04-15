package datn.datn_expansemanagement.screen.overview.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.overview.presentation.model.ExchangeRateViewModel

class OverviewMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        // chưa có api thì add cứng vô đây, có api thì add theo api
        for (i in 1..3){
            listReturn.add(ExchangeRateViewModel(
                id = i,
                exchange = "1 VGO ~ 482000d",
                name = "VGO"
            ))
        }
        return listReturn
    }

}