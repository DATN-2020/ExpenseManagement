package datn.datn_expansemanagement.screen.add_plan.presentation

import datn.datn_expansemanagement.screen.add_plan.domain.AddPlanMapper
import datn.datn_expansemanagement.screen.add_plan.domain.GetTimeMapper
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

class AddPlanPresenter : AddPlanContract.Presenter() {
    override fun getData(typeAdd: PlanItemViewModel) {
        when (typeAdd.type) {
            PlanItemViewModel.Type.BUDGET -> {
                view?.showData(AddPlanMapper().map(""))
            }
            PlanItemViewModel.Type.TRANSACTION -> {
            }
            else -> {

            }
        }

    }

    override fun getTime() {
        view?.showListTime(GetTimeMapper().map(""))
    }
}