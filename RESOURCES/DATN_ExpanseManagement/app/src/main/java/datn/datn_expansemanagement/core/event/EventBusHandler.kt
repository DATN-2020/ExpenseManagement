package datn.datn_expansemanagement.core.event

interface EventBusHandler {
    fun onReceiveEvent(data: EventBusData)
}