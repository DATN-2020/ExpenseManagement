package datn.datn_expansemanagement.screen.overview.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.overview.presentation.renderer.EmptyLineViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.renderer.OverviewExchangeRateViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.renderer.OverviewHistoryCurrentlyViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.renderer.OverviewTotalMoneyViewRenderer
import kotlinx.android.synthetic.main.layout_overview.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class OverviewView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), OverviewContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
    AndroidMvpView.LayoutViewCreator(R.layout.layout_overview, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = OverviewPresenter(AndroidScreenNavigator(mvpActivity))
    private val mResource = OverviewResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp : ListViewMvp? = null

    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    override fun initCreateView() {
        initRecycleView()
    }

    private val onActionNotify = object : OnActionNotify{
        override fun onActionNotify() {
            mPresenter.gotoExchangeRateActivity()
        }

    }

    private val onGotoReportTotalMoney = object : OnActionNotify{
        override fun onActionNotify() {

        }
    }
    private val onGotoHistory = object : OnActionNotify{
        override fun onActionNotify() {
            mPresenter.gotoHistoryActivity()
        }
    }


    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvOverview, renderConfig)
        listViewMvp?.addViewRenderer(OverviewTotalMoneyViewRenderer(mvpActivity, onGotoReportTotalMoney))
        listViewMvp?.addViewRenderer(OverviewHistoryCurrentlyViewRenderer(mvpActivity, onGotoHistory))
        listViewMvp?.addViewRenderer(OverviewExchangeRateViewRenderer(mvpActivity, onActionNotify))
        listViewMvp?.addViewRenderer(EmptyLineViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if(list.isNotEmpty()){
            this.listData.addAll(list)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }
}