package datn.datn_expansemanagement.screen.overview.presentation

import android.content.Context
import android.view.ViewGroup
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity

class OverviewView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), OverviewContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
    AndroidMvpView.LayoutViewCreator(R.layout.layout_overview, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = OverviewPresenter()
    private val mResource = OverviewResource()

    override fun initCreateView() {

    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
}