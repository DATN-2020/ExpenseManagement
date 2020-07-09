package datn.datn_expansemanagement.screen.information.presentation

import datn.datn_expansemanagement.screen.information.domain.InformationMapper

class InformationPresenter : InformationContract.Presenter(){
    override fun getData() {
        view?.showData(InformationMapper().map(""))
    }

}