package datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle

import datn.datn_expansemanagement.core.base.presentation.mvp.base.lifecycle.LifeCycleMvpView

interface LifeCycleAndroidMvpView : LifeCycleMvpView {
    fun onViewResult(viewResult: ViewResult)
    fun onRequestPermissionsResult(permissionsResult: PermissionsResult)
}