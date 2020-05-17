package datn.datn_expansemanagement.core.event

interface EventBusManager  : EventBusPublisher {
    fun register(handler: EventBusHandler)
    fun unregister(handler: EventBusHandler)
}