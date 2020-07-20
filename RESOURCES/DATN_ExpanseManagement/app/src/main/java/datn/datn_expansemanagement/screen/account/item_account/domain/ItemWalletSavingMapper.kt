package datn.datn_expansemanagement.screen.account.item_account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletSavingResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel

class ItemWalletSavingMapper : Mapper<WalletSavingResponse, MutableList<ViewModel>>{
    override fun map(input: WalletSavingResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val listItem = mutableListOf<ViewModel>()
        var totalPrice = 0.0
        if(!input.data.isNullOrEmpty()){
            input.data.forEach {
                listItem.add(ItemAccountAccumulationViewModel(
                    id = it.idSaving.getValueOrDefaultIsZero(),
                    price = it.price.getValueOrDefaultIsZero(),
                    name = it.name.getValueOrDefaultIsEmpty(),
                    endDate = it.dateE.getValueOrDefaultIsEmpty(),
                    startDate = it.dateS.getValueOrDefaultIsEmpty(),
                    isFinish = it.isFinish.getValueOrDefault()
                ))
                totalPrice += it.price
            }
        }
        if (listItem.isNotEmpty()) {
            val dataLast = listItem.last() as ItemAccountAccumulationViewModel
            dataLast.isLast = true
        }
        listReturn.add(ItemAccountTotalMoneyViewModel(total = totalPrice))
        listReturn.addAll(listItem)
        return listReturn
    }

}