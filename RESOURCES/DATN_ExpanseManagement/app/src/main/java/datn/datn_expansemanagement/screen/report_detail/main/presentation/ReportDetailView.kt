package datn.datn_expansemanagement.screen.report_detail.main.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.report.presentation.ReportResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report.presentation.renderer.*
import datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer.ReportDetailDayViewRenderer
import datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer.ReportDetailHeaderViewRenderer
import datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer.ReportDetailItemViewRenderer
import datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer.ReportDetailPieChartViewRenderer
import kotlinx.android.synthetic.main.layout_report_detail.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ReportDetailView (mvpActivity: MvpActivity, viewCreator: LayoutViewCreator,
private val data : ReportViewModel? = null): AndroidMvpView(mvpActivity, viewCreator), ReportDetailContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report_detail, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportDetailPresenter(mvpActivity)
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null

    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        view.imgAdd.gone()
        view.tvToolbar.text = "Báo cáo chi tiết"
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }

        initRecycleView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData(data)
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
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private val mResourceProvider = ReportResource(mvpActivity)
    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvReportDetail, renderConfig)
        listViewMvp?.addViewRenderer(ReportBalanceViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportDetailDayViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportDetailItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportDetailHeaderViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportDetailPieChartViewRenderer(mvpActivity, mResourceProvider))
        listViewMvp?.createView()
    }
}