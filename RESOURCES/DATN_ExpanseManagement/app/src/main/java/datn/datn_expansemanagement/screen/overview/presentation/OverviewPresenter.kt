package datn.datn_expansemanagement.screen.overview.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.overview.domain.OverviewMapper

class OverviewPresenter(private val screenNavigator: AndroidScreenNavigator) : OverviewContract.Presenter(){
    override fun getData() {
        view?.showData(OverviewMapper().map(""))
    }

    override fun gotoExchangeRateActivity() {
        screenNavigator.gotoExchangeRateActivity()
    }
}