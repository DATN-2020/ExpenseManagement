package datn.datn_expansemanagement.screen.information.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.screen.information.domain.InformationMapper

class InformationPresenter(private val screenNavigator: AndroidScreenNavigator) : InformationContract.Presenter(){
    override fun getData(passportResponse: PassportResponse) {
        view?.showData(InformationMapper().map(passportResponse))
    }

    override fun gotoLoginActivity() {
        screenNavigator.gotoLoginActivity(isLogin = true)
    }
}