package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.GetBudgetResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.NoDataItemViewModel
import java.text.SimpleDateFormat

class ItemTabBudgetMapper(private val tab: TabItemViewModel) :
        Mapper<GetBudgetResponse, MutableList<ViewModel>> {
    override fun map(input: GetBudgetResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val listReturn = mutableListOf<ViewModel>()

        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                list.add(
                        BudgetItemViewModel(
                                id = it.idBudget.getValueOrDefaultIsZero(),
                                name = it.name.getValueOrDefaultIsEmpty(),
                                imgUrl = it.image.getValueOrDefaultIsEmpty(),
                                totalPrice = it.amount.getValueOrDefaultIsZero(),
                                currentPrice = it.remain.getValueOrDefaultIsZero(),
                                isFinish = it.check.getValueOrDefault(),
                                idWallet = it.idwallet.getValueOrDefaultIsEmpty().toInt(),
                                startDate = Utils.convertDateFormat(it.dateTimeS, SimpleDateFormat("yyyy-MM-dd"),
                                        SimpleDateFormat("dd/MM/yyyy")),
                                endDate = Utils.convertDateFormat(it.dateTimeE, SimpleDateFormat("yyyy-MM-dd"),
                                        SimpleDateFormat("dd/MM/yyyy"))
                        )
                )
            }
        }

        if (tab.id == 0) {
            list.forEach {
                when (it) {
                    is BudgetItemViewModel -> {
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
                    is BudgetItemViewModel -> {
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