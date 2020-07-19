package datn.datn_expansemanagement.screen.trip.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.request.AddTripRequest
import datn.datn_expansemanagement.domain.request.TripRequest
import datn.datn_expansemanagement.domain.request.UpdateTripRequest

interface TripContract {
    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showData(list: MutableList<ViewModel>)
        fun reload(type: String)
    }

    abstract class Presenter : MvpPresenter<View>(){
        abstract fun getData(request: TripRequest)
        abstract fun addTrip(request: AddTripRequest)
        abstract fun deleteTrip(idTrip: Int)
        abstract fun updateTrip(idTrip: Int, request: UpdateTripRequest)
    }
}