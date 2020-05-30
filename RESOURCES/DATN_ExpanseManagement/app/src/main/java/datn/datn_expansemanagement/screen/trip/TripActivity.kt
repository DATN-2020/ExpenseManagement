package datn.datn_expansemanagement.screen.trip

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.trip.presentation.TripView

class TripActivity: MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        return TripView(this, TripView.ViewCreator(this, null))
    }
}