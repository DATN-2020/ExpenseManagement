package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TransactionResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.NoDataItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.TransactionItemViewModel
import java.text.SimpleDateFormat

class ItemTransactionMapper(private val tab: TabItemViewModel) :
    Mapper<TransactionResponse, MutableList<ViewModel>> {
    override fun map(input: TransactionResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val listReturn = mutableListOf<ViewModel>()
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                list.add(
                    TransactionItemViewModel(
                        id = it.idPeriodic.getValueOrDefaultIsZero(),
                        name = it.name.getValueOrDefaultIsEmpty(),
                        imgUrl = it.image.getValueOrDefaultIsEmpty(),
                        price = it.amount.getValueOrDefaultIsZero().toDouble(),
                        endDate = it.dateE.getValueOrDefaultIsEmpty(),
                        startDate = it.dateS.getValueOrDefaultIsEmpty(),
                        isFinish = it.isFinish.getValueOrDefault(),
                        currentDate = Utils.convertDateFormat(
                            it.dateTimeS.getValueOrDefaultIsEmpty(),
                            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
                            SimpleDateFormat("dd/MM/yyyy")
                        )
                    )
                )
            }
        }

        if (tab.id == 0) {
            list.forEach {
                when (it) {
                    is TransactionItemViewModel -> {
                        if (!it.isFinish) {
                            listReturn.add(it)
                        }
                    }
                }
            }
        }

        if (tab.id == 1) {
            list.forEach {
                when (it) {
                    is TransactionItemViewModel -> {
                        if (it.isFinish) {
                            listReturn.add(it)
                        }
                    }
                }
            }
        }

        if (listReturn.isNullOrEmpty()) {
            listReturn.add(NoDataItemViewModel())
        }

        return listReturn
    }

}