package datn.datn_expansemanagement.core.base.presentation.mvp.android

import android.content.Context
import android.view.*
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.LifeCycleAndroidDispatcher
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.LifeCycleAndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.PermissionsResult
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.base.lifecycle.LifeCycleDispatcherSetter

abstract class AndroidMvpView(val mvpActivity: MvpActivity, viewCreator: ViewCreator) : MvpView,
    LifeCycleAndroidMvpView, LifeCycleDispatcherSetter<LifeCycleAndroidMvpView> {

    protected val view: View = viewCreator.createView()
    private var lifeCycleDispatcher: LifeCycleAndroidDispatcher = LifeCycleAndroidDispatcher()
    private var isFirstTimeInitData = true

    fun createView(): View {
        initCreateView()
        return view
    }

    protected abstract fun initCreateView()

    override fun addLifeCycle(lifeCycle: LifeCycleAndroidMvpView) {
        lifeCycleDispatcher.addLifeCycle(lifeCycle)
    }

    @CallSuper
    override fun initMvpView() {
        lifeCycleDispatcher.initMvpView()
    }

    override fun initData() {
    }

    @CallSuper
    override fun startMvpView() {
        lifeCycleDispatcher.startMvpView()
        if (isFirstTimeInitData) {
            isFirstTimeInitData = false
            initData()
        }
    }

    override fun isHandleBackPressed(): Boolean {
        return lifeCycleDispatcher.isHandleBackPressed()
    }

    @CallSuper
    override fun stopMvpView() {
        lifeCycleDispatcher.stopMvpView()
    }

    interface ViewCreator {
        fun createView(): View
    }

    class ViewRootViewCreator(var view: View) : ViewCreator {
        override fun createView(): View = view
    }

    open class LayoutViewCreator(
        @LayoutRes private val layoutId: Int, private val context: Context,
        private val viewGroup: ViewGroup?
    ) : ViewCreator {
        override fun createView(): View =
            LayoutInflater.from(context).inflate(layoutId, viewGroup, false)
    }

    @CallSuper
    override fun onViewResult(viewResult: ViewResult) {
        lifeCycleDispatcher.onViewResult(viewResult)
    }

    @CallSuper
    override fun onRequestPermissionsResult(permissionsResult: PermissionsResult) {
        lifeCycleDispatcher.onRequestPermissionsResult(permissionsResult)
    }
}