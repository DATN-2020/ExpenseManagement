package datn.datn_expansemanagement.screen.overview.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.overview.presentation.renderer.ExchangeReteViewRenderer
import kotlinx.android.synthetic.main.layout_overview.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class OverviewView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), OverviewContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
    AndroidMvpView.LayoutViewCreator(R.layout.layout_overview, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = OverviewPresenter()
    private val mResource = OverviewResource()
    private val listExchangeRate = mutableListOf<ViewModel>()
    private var rvExchangeRate : ListViewMvp? = null

    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    override fun initCreateView() {
        initRecycleView()
    }

    private fun initRecycleView(){
        rvExchangeRate = ListViewMvp(mvpActivity, view.rvOverview, renderConfig)
        rvExchangeRate?.addViewRenderer(ExchangeReteViewRenderer(mvpActivity))
        rvExchangeRate?.createView()
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
        this.listExchangeRate.clear()
        if(list.isNotEmpty()){
            this.listExchangeRate.addAll(list)
        }
        rvExchangeRate?.setItems(this.listExchangeRate)
        rvExchangeRate?.notifyDataChanged()
    }
}