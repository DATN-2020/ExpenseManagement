package datn.datn_expansemanagement.screen.account.item_account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.common.AppConstants
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletSavingResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
                    isFinish = it.isFinish.getValueOrDefault(),
                    maxProcess = dateToDays(convertStringToDate(it.dateE)!!).toInt()
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

    private fun dateToDays(date: Date): Int {
        //  convert a date to an integer and back again
        val currentTime = date.time
        return (currentTime / AppConstants.MAGIC).toInt()
    }

    private fun convertStringToDate(value: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return try {
            format.parse(value)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

}