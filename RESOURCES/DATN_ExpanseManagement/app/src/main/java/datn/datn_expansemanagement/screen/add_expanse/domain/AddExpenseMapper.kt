package datn.datn_expansemanagement.screen.add_expanse.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel

class AddExpenseMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(AddExpenseViewModel(
            type = AddExpenseViewModel.Type.DONATE,
            isChoose = true
        ))
        listReturn.add(AddExpenseViewModel(
            type = AddExpenseViewModel.Type.RECEIVE,
            isLast = true
        ))
        return listReturn
    }

}