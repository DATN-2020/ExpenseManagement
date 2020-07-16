package datn.datn_expansemanagement.screen.add_plan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_plan.presentation.model.*
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

class AddPlanMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddPlanPriceViewModel())
        list.add(AddPlanCategoryViewModel())
        list.add(AddPlanChooseDateViewModel())
        list.add(AddPlanDateViewModel())
        list.add(AddPlanWalletViewModel())
        return list
    }

}