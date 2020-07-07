package datn.datn_expansemanagement.screen.report.presentation

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report.presentation.renderer.ReportBarChartViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.renderer.ReportPieChartViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.renderer.ReportViewRenderer
import datn.datn_expansemanagement.view.custom_charts.CustomBarChart
import kotlinx.android.synthetic.main.layout_report.view.*
import kotlinx.android.synthetic.main.layout_report_receive.view.*
import kotlinx.android.synthetic.main.toolbar_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ReportView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ReportContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportPresenter(AndroidScreenNavigator(mvpActivity))
    private val mResource = ReportResource(mvpActivity)
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp : ListViewMvp? = null

    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val onItemClick = object : OnItemRvClickedListener<ViewModel>{
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as ReportViewModel
            mPresenter.gotoReportDetailActivity(dataItem)
        }

    }

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
//        listViewMvp?.addViewRenderer(ReportViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportBarChartViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(ReportPieChartViewRenderer(mvpActivity, mResource))
//        listViewMvp?.setOnItemRvClickedListener(onItemClick)
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