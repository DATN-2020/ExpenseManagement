package datn.datn_expansemanagement.screen.add_plan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_plan.presentation.model.*

class AddTransactionMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddPlanPriceViewModel(title = "Số tiền"))
        list.add(AddPlanCategoryViewModel())
        list.add(AddPlanDesViewModel())
        list.add(AddPlanChooseDateViewModel())
        list.add(AddPlanDateViewModel())
        list.add(AddPlanWalletViewModel())
        return list
    }

}