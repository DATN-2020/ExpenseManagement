package datn.datn_expansemanagement.screen.exchange_rate.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.ExchangeRateResponse
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.exchange_rate.presentation.model.ExchangeRateViewModel

class ExchangeRateMapper : Mapper<ExchangeRateResponse, MutableList<ViewModel>>{
    override fun map(input: ExchangeRateResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.list.isNullOrEmpty()){
           input.list.forEach {
               list.add(ExchangeRateViewModel(
                   id = it.type.getValueOrDefaultIsEmpty(),
                   moneyBuy = it.muatienmat.getValueOrDefaultIsEmpty(),
                   moneySell = it.bantienmat.getValueOrDefaultIsEmpty(),
                   imageurl = it.imageurl.getValueOrDefaultIsEmpty()
               ))
           }
        }
        return list
    }

}