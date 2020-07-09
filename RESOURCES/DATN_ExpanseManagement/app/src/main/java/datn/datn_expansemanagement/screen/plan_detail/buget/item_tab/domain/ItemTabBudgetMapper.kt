package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel

class ItemTabBudgetMapper(private val tab: TabItemViewModel) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()

        when(tab.typePlanId){
            // kiem tra input xem cái nào đã hoàn thành rồi thì so với tab id
            PlanItemViewModel.Type.BUDGET->{
                list.add(BudgetItemViewModel(
                    id = 1,
                    name = "Cho vay",
                    totalPrice = 5000000.0,
                    currentPrice = 250000.0,
                    isFinish = false
                ))
            }
            PlanItemViewModel.Type.BUDGET->{}
            else->{

            }
        }
        return list
    }

}