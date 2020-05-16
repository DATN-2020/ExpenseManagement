package datn.datn_expansemanagement.screen.add_expense_donate.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateCategoryViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateTotalMoneyViewModel

class AddExpenseDonateMapper: Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(AddExpenseDonateTotalMoneyViewModel())
        listReturn.add(AddExpenseDonateCategoryViewModel())
        listReturn.add(AddExpenseDonateInfoViewModel())
        return listReturn
    }

}