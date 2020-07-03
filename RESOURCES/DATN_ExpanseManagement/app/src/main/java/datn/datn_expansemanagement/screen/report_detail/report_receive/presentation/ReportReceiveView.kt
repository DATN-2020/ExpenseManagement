package datn.datn_expansemanagement.screen.report_detail.report_receive.presentation

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.ReportDetailResource
import datn.datn_expansemanagement.view.custom_charts.CustomBarChart
import kotlinx.android.synthetic.main.layout_report_receive.view.*

class ReportReceiveView(
    mvpActivity: MvpActivity, viewCreator: LayoutViewCreator,
    private val extra: ReportViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), ReportReceiveContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report_receive, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportReceivePresenter()
    private val mResource = ReportDetailResource()

    override fun initCreateView() {
//        initChart()
        initCustomChart()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    private fun initCustomChart() {
        val chart = view.customChart
        chart.extraTopOffset = -30f
        chart.extraBottomOffset = 10f
        chart.description.isEnabled = false
        chart.setBackgroundColor(mResource.getBackgroundChart())
        chart.setDrawGridBackground(false)
        chart.setPinchZoom(true)
        chart.fitScreen()
        chart.enableScroll()
        chart.clear()

        setLabelBottomChart(chart)
        setLabelLeftRightChart(chart)
        setData(chart)
    }

    private fun setLabelLeftRightChart(chart: CustomBarChart){
        val aXisLeft = chart.axisLeft
        aXisLeft.valueFormatter = object : ValueFormatter(){
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                var temp = ""
                temp = if(value != 0f){
                    Utils.formatMoney(value.toDouble())
                }else{
                    "0"
                }
                return temp
            }
        }


        val aXisRight = chart.axisRight
        aXisRight.isEnabled = false
    }

    private fun setLabelBottomChart(chart: CustomBarChart){
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(true)
        xAxis.setDrawAxisLine(false)
        xAxis.enableGridDashedLine(20f, 20f, 0f)
        xAxis.granularity = 1f
        xAxis.textColor = Color.BLACK
        xAxis.textSize = 10f
        xAxis.labelCount = 15
        xAxis.setCenterAxisLabels(false)
    }

    private fun setData(chart: CustomBarChart) {
        val dataSet = BarDataSet(getListEntry(), "Tổng chi tiêu")
        dataSet.color = mResource.getColorChart()
        dataSet.valueTextColor = mResource.getTextChartColor()
        dataSet.valueTextSize = 12f

        val data = BarData(dataSet)
        data.barWidth = 0.9f
        chart.data = data
        chart.invalidate()
    }

//    private fun initChart(){
//
//        // set data
//        val dataSet = BarDataSet(getListEntry(), "Tổng chi tiêu")
//        dataSet.color = mResource.getColorChart()
//        dataSet.valueTextColor = mResource.getTextChartColor()
//        dataSet.valueTextSize = 12f
//        view.chart.animateY(800)
////        dataSet.setDrawIcons(false)
//
//        val barData = BarData(dataSet)
//        barData.barWidth = 0.9f
//        view.chart.description.text = ""
//        view.chart.data = barData
//        view.chart.invalidate()
//
//        view.chart.setBackgroundColor(mResource.getBackgroundChart()) // màu nền chart
//        view.chart.setNoDataText("Không có dữ liệu")
//        view.chart.setDrawGridBackground(false) // line background
//        view.chart.setDrawBorders(false) // duong vien xung quanh
//        view.chart.setPinchZoom(false)
//        view.chart.setDrawValueAboveBar(true)
//        view.chart.setDrawBarShadow(false) // bar background empty
//        view.chart.setFitBars(true)
////
////        val xAxisFormatter = DateValueFormatter() // set label for x axis
//        val xAxis = view.chart.xAxis
//        xAxis.valueFormatter = IndexAxisValueFormatter(listLabel())
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.granularity = 1f // spacing
//        xAxis.labelCount = listLabel().size
//        xAxis.setCenterAxisLabels(true)
//        xAxis.setDrawGridLines(false)
//        xAxis.removeAllLimitLines()
//
//        val rightAxis = view.chart.axisRight
//        rightAxis.isEnabled = false
//
//        val leftAxis = view.chart.axisLeft
//        leftAxis.granularity = 1f
//        leftAxis.labelCount = 5
//        leftAxis.spaceTop = 30f
//
//        val legend = view.chart.legend
//        legend.formSize = 8f
//        legend.xEntrySpace = 4f
//
//
////        xAxis.setDrawGridLines(false)
////        xAxis.valueFormatter = xAxisFormatter
//    }

    private fun listLabel(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..12) {
            list.add(i.toString())
        }
        list.add("Tháng")
        return list
    }

    private fun getListEntry(): ArrayList<BarEntry> {
        val list = ArrayList<BarEntry>()
        for (i in 1..12) {
            list.add(BarEntry(i.toFloat(), Math.random().toFloat() * 1000000))
        }
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