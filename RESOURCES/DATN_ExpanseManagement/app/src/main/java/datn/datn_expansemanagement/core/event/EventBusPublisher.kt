package datn.datn_expansemanagement.core.event

interface EventBusPublisher {
    fun publishEvent(data: EventBusData)
}