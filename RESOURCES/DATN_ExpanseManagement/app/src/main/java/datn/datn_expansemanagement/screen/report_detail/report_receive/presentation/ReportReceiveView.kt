package datn.datn_expansemanagement.screen.report_detail.report_receive.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.custom_chart.DateValueFormatter
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import kotlinx.android.synthetic.main.layout_report_receive.view.*

class ReportReceiveView (mvpActivity: MvpActivity, viewCreator: LayoutViewCreator,
private val extra: ReportViewModel?): AndroidMvpView(mvpActivity, viewCreator), ReportReceiveContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report_receive, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportReceivePresenter()

    override fun initCreateView() {
        initChart()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    private fun initChart(){

        // set data
        val dataSet = BarDataSet(getListEntry(), "")
        dataSet.setDrawIcons(false)
        val barData = BarData(dataSet)
        barData.barWidth = 0.9f
        barData.setValueTextSize(10f)
        view.chart.description.text = ""
        view.chart.data = barData
        val color = ContextCompat.getColor(mvpActivity, android.R.color.holo_blue_light)

        view.chart.setPinchZoom(true) // slide chart x ,y
        view.chart.setDrawGridBackground(false) // line background
        view.chart.setDrawBarShadow(false) // bar background empty

        val xAxisFormatter = DateValueFormatter() // set label for x axis

        val xAxis = view.chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 2f // spacing
        xAxis.valueFormatter = xAxisFormatter
//
//        val listLabel = ArrayList<Int>()
//        for(i in 1..12){
//            listLabel.add(i)
//        }
    }

    private fun getListEntry(): ArrayList<BarEntry>{
        val list = ArrayList<BarEntry>()
        list.add(BarEntry(4f, 4f))
        list.add(BarEntry(8f, 5f))
        return list
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
    }

}