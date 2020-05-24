package datn.datn_expansemanagement.screen.trip.item_trip.presentation

import datn.datn_expansemanagement.screen.trip.item_trip.domain.ItemTripMapper

class ItemTripPresenter : ItemTripContract.Presenter(){
    override fun getData(isFinished: Boolean?) {
        view?.showData(ItemTripMapper(isFinished).map(""))
    }
}