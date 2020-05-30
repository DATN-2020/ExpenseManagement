package datn.datn_expansemanagement.screen.location

import android.os.Bundle
import com.mapbox.mapboxsdk.maps.MapView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.location.presentation.LocationView

class LocationActivity : MvpActivity(){

    lateinit var view: LocationView
    lateinit var mapView: MapView

    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        view =  LocationView(this, LocationView.ViewCreator(this, null))
        mapView = view.getMapView()
        mapView.onCreate(savedInstanceState)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}