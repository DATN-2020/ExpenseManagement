package datn.datn_expansemanagement.screen.trip.presentation

import datn.datn_expansemanagement.screen.trip.domain.TripMapper

class TripPresenter : TripContract.Presenter(){
    override fun getData() {
        view?.showData(TripMapper().map(""))
    }
}