package datn.datn_expansemanagement.screen.add_plan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanCategoryViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanDateViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanPriceViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanWalletViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

class AddPlanMapper(private val typeAdd: TypeAddViewModel) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(typeAdd.isBudget){
            list.add(AddPlanPriceViewModel())
            list.add(AddPlanCategoryViewModel())
            list.add(AddPlanDateViewModel())
            list.add(AddPlanWalletViewModel())
        }
        return list
    }

}