package datn.datn_expansemanagement.screen.report.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.report.presentation.renderer.ReportViewRenderer
import kotlinx.android.synthetic.main.layout_report.view.*
import kotlinx.android.synthetic.main.toolbar_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ReportView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ReportContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportPresenter()
    private val mResource = ReportResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp : ListViewMvp? = null

    private val renderInput = GridRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = GridRenderConfigFactory.Orientation.VERTICAL,
        spanCount = 2
    )

    private val renderConfig = GridRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        initRecycleView()
        initView()
    }

    private fun initView(){
        view.tvToolbar.text = mResource.getTextTitle()
        view.imgAdd.gone()
    }


    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvReport, renderConfig)
        listViewMvp?.addViewRenderer(ReportViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if(list.isNotEmpty()){
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
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
}