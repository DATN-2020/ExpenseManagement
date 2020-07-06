package datn.datn_expansemanagement.screen.add_plan.presentation

import datn.datn_expansemanagement.screen.add_plan.domain.AddPlanMapper
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

class AddPlanPresenter : AddPlanContract.Presenter(){
    override fun getData(typeAdd: TypeAddViewModel) {
        view?.showData(AddPlanMapper(typeAdd).map(""))
    }

}