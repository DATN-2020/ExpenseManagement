package datn.datn_expansemanagement.core.base.presentation.mvp.base.lifecycle

interface LifeCycleDispatcherSetter<in T: LifeCycleMvpView> {
    fun addLifeCycle(lifeCycle: T)
}