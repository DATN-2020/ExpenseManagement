package datn.datn_expansemanagement.screen.add_expense_donate.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.common.AppConstants
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.GetBudgetResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GetBudgetMapper : Mapper<GetBudgetResponse, MutableList<ViewModel>> {
    override fun map(input: GetBudgetResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                val start = dateToDays(convertStringToDate(it.dateTimeS)!!)
                val current = dateToDays(convertStringToDate(getCurrentDate())!!)
                val end = dateToDays(convertStringToDate(it.dateTimeE)!!)
                if (current in start.. end) {
                    if (!it.check) {
                        list.add(
                                BudgetItemViewModel(
                                        id = it.idBudget.getValueOrDefaultIsZero(),
                                        name = it.name.getValueOrDefaultIsEmpty(),
                                        imgUrl = it.image.getValueOrDefaultIsEmpty(),
                                        totalPrice = it.amount.getValueOrDefaultIsZero(),
                                        currentPrice = it.remain.getValueOrDefaultIsZero(),
                                        isFinish = it.check.getValueOrDefault(),
                                        idWallet = it.idwallet.getValueOrDefaultIsEmpty().toInt()
                                )
                        )
                    }
                }
            }
        }
        return list
    }

    private fun getCurrentDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
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