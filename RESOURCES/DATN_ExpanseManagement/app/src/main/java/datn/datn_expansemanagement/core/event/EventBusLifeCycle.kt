package datn.datn_expansemanagement.core.event

import android.view.MotionEvent
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.LifeCycleAndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.PermissionsResult
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult

class EventBusLifeCycle constructor(private val onEventBusActionData: OnActionData<EventBusData>) : LifeCycleAndroidMvpView, EventBusHandler {
    override fun onReceiveEvent(data: EventBusData) {
        onEventBusActionData.onAction(data)
    }

    override fun onViewResult(viewResult: ViewResult) {
    }

    override fun onRequestPermissionsResult(permissionsResult: PermissionsResult) {
    }

    override fun initMvpView() {
    }

    override fun startMvpView() {
        EventBusLifeCycleManager.getInstance()?.register(this)
    }

    override fun initData() {
    }

    override fun stopMvpView() {
        EventBusLifeCycleManager.getInstance()?.unregister(this)
    }

    override fun isHandleBackPressed(): Boolean = false

    fun sendData(data:EventBusData){
        EventBusLifeCycleManager.getInstance()?.publishEvent(data)
    }
}