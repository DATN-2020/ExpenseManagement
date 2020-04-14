package datn.datn_expansemanagement.core.base.presentation.mvp.base

interface BaseMvpPresenter<in V: MvpView> {

    fun attachView(view: V)

    fun detachView()
}