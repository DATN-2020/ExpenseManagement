package datn.datn_expansemanagement.screen.add_expense_donate.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.GetBudgetResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel

class GetBudgetMapper : Mapper<GetBudgetResponse, MutableList<ViewModel>> {
    override fun map(input: GetBudgetResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                if(!it.check){
                    list.add(
                        BudgetItemViewModel(
                            id = it.idBudget.getValueOrDefaultIsZero(),
                            name = it.name.getValueOrDefaultIsEmpty(),
                            imgUrl = it.image.getValueOrDefaultIsEmpty(),
                            totalPrice = it.amount.getValueOrDefaultIsZero(),
                            currentPrice = it.remain.getValueOrDefaultIsZero(),
                            isFinish = it.check.getValueOrDefault()
                        )
                    )
                }
            }
        }
        return list
    }

}