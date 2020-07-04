package datn.datn_expansemanagement.screen.main_plan.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.main_plan.domain.OverviewMapper
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel

class OverviewPresenter(private val screenNavigator: AndroidScreenNavigator) : OverviewContract.Presenter(){
    override fun getData() {
        view?.showData(OverviewMapper().map(""))
    }

    override fun gotoExchangeRateActivity() {
        screenNavigator.gotoExchangeRateActivity()
    }

    override fun gotoHistoryActivity() {
        screenNavigator.gotoHistoryActivity()
    }

    override fun gotoPlanDetailActivity(planType: PlanItemViewModel) {
        screenNavigator.gotoPlanDetailActivity(planType)
    }
}