package datn.datn_expansemanagement.screen.add_expense_receive.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveTotalMoneyViewModel

class AddExpenseReceiveMapper: Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(AddExpenseReceiveTotalMoneyViewModel())
        listReturn.add(AddExpenseCategoryViewModel())
        listReturn.add(AddExpenseReceiveInfoViewModel())
        return listReturn
    }

}