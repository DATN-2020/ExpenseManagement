package datn.datn_expansemanagement.core.base.presentation.mvp.base.lifecycle

interface LifeCycleMvpView {
    fun initMvpView()
    fun startMvpView()
    fun initData()
    fun stopMvpView()
    fun isHandleBackPressed(): Boolean
}