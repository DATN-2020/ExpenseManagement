package datn.datn_expansemanagement.screen.add_expense_loan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.model.AddExpenseLoanRequireViewModel
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.model.AddExpenseLoanTotalMoneyViewModel

class AddExpenseLoanMapper(private val isDonate: Boolean = false) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddExpenseLoanTotalMoneyViewModel(isDonate))
        list.add(AddExpenseLoanRequireViewModel(isLoan = isDonate))
//        list.add(AddExpenseDonateInfoViewModel())
        return list
    }

}