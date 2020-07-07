package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.graphics.Color
import android.view.View
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.ReportResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBarChartViewModel
import datn.datn_expansemanagement.view.custom_charts.CustomBarChart
import kotlinx.android.synthetic.main.layout_report_receive.view.*
import kotlin.random.Random

class ReportBarChartViewRenderer (context: Context, private val mResource : ReportResource): ViewRenderer<ReportBarChartViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_bar_chart
    }

    override fun getModelClass(): Class<ReportBarChartViewModel> = ReportBarChartViewModel::class.java

    override fun bindView(model: ReportBarChartViewModel, viewRoot: View) {
        initCustomChart(view = viewRoot, model = model)
    }

    private fun initCustomChart(view: View, model: ReportBarChartViewModel) {
        val chart = view.customChart
        chart.clear()
        chart.extraTopOffset = -30f
        chart.extraBottomOffset = 10f
        chart.description.isEnabled = false
        chart.setBackgroundColor(mResource.getBackgroundChart())
        chart.setDrawGridBackground(false)
        chart.setPinchZoom(true)
        chart.fitScreen()
        chart.enableScroll()
        chart.setVisibleXRangeMaximum(6f)


        setLabelBottomChart(chart)
        setLabelLeftRightChart(chart)
        setLegend(chart)
        setData(chart, model.list)
    }

//    private fun getListEntry(): ArrayList<BarEntry> {
//        val list = ArrayList<BarEntry>()
//        for (i in 1..12) {
//            val random = Random.nextInt(-10, 10)
//            list.add(BarEntry(i.toFloat(), random.toFloat() * 10))
//        }
//        return list
//    }

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
        aXisLeft.setDrawLabels(true)
        aXisLeft.spaceTop = 25f
        aXisLeft.spaceBottom = 25f
        aXisLeft.textColor = Color.BLACK
        aXisLeft.textSize = 14f
        aXisLeft.typeface = mResource.getTypeFaceMedium()
        aXisLeft.setDrawAxisLine(false)
        aXisLeft.setDrawGridLines(true)
        aXisLeft.setDrawZeroLine(true) // draw a zero line
        aXisLeft.enableAxisLineDashedLine(20f, 20f, 0f)

        val aXisRight = chart.axisRight
        aXisRight.isEnabled = false
    }

    private fun setLegend(chart: CustomBarChart){
        val legend = chart.legend
        legend.isEnabled = false
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

    private fun setData(chart: CustomBarChart, list: ArrayList<BarEntry>) {
        val dataSet = BarDataSet(list, "Tổng chi tiêu")
        dataSet.color = mResource.getColorChart()
        dataSet.valueTextColor = mResource.getTextChartColor()
        dataSet.valueTextSize = 12f

        val data = BarData(dataSet)
        data.barWidth = 0.9f
        chart.data = data
        chart.invalidate()
    }

}