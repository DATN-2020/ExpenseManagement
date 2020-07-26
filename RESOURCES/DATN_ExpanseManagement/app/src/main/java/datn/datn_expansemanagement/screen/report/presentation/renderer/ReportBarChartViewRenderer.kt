package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.report.data.ReportDetailExtra
import datn.datn_expansemanagement.screen.report.presentation.ReportResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportBarChartViewModel
import datn.datn_expansemanagement.view.custom_charts.CustomBarChart
import kotlinx.android.synthetic.main.item_layout_report_bar_chart.view.*
import kotlinx.android.synthetic.main.layout_report_receive.view.*
import kotlinx.android.synthetic.main.layout_report_receive.view.customChart
import kotlin.random.Random

class ReportBarChartViewRenderer(context: Context, private val mResource: ReportResource,
                                 private val onChooseValueChart: OnActionData<ReportDetailExtra>) : ViewRenderer<ReportBarChartViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_bar_chart
    }

    override fun getModelClass(): Class<ReportBarChartViewModel> = ReportBarChartViewModel::class.java

    override fun bindView(model: ReportBarChartViewModel, viewRoot: View) {
        initCustomChart(view = viewRoot, model = model)
    }

    private fun initCustomChart(view: View, model: ReportBarChartViewModel) {
        val chart = view.customChart
        chart.extraTopOffset = -30f
        chart.extraBottomOffset = 10f
        chart.description.isEnabled = false

        chart.setBackgroundColor(mResource.getBackgroundChart())
        chart.setDrawGridBackground(false)
        chart.setPinchZoom(true)
        chart.fitScreen()
        chart.enableScroll()
//        chart.setVisibleXRangeMaximum(6f)
        chart.animateY(1000)

        setLabelBottomChart(chart)
        setLabelLeftRightChart(chart)
        setLegend(chart)

        if (model.list.isNullOrEmpty()) {
            view.tvNoData.visible()
        } else {
            view.tvNoData.gone()
            setData(chart, model.list)
        }

        chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onNothingSelected() {
            }

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e?.y.getValueOrDefaultIsZero().toInt() != 0) {
                    val extra = ReportDetailExtra(
                            month = e?.x.getValueOrDefaultIsZero().toInt()
                    )
                    EventFireUtil.fireEvent(onChooseValueChart, extra)
                }

            }

        })

    }

    private fun setLabelLeftRightChart(chart: CustomBarChart) {
        val aXisLeft = chart.axisLeft
        aXisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                var temp = ""
                temp = if (value.toInt() != 0) {
                    Utils.formatMoney(value.toDouble() / 1000000)
                } else {
                    ""
                }
                return temp
            }
        }
        aXisLeft.setDrawLabels(true)
        aXisLeft.spaceTop = 25f
        aXisLeft.spaceBottom = 25f
        aXisLeft.textColor = Color.BLACK
        aXisLeft.textSize = 14f
        aXisLeft.textSize = 10f
        aXisLeft.textColor = mResource.getTextLabelChart()
        aXisLeft.typeface = mResource.getTypeFaceRegular()
        aXisLeft.setDrawAxisLine(false)
        aXisLeft.setDrawGridLines(true)
        aXisLeft.setDrawZeroLine(true) // draw a zero line
        aXisLeft.enableAxisLineDashedLine(20f, 20f, 0f)

        val aXisRight = chart.axisRight
        aXisRight.isEnabled = false
    }

    private fun setLegend(chart: CustomBarChart) {
        val legend = chart.legend
        legend.isEnabled = false
    }

    private fun setLabelBottomChart(chart: CustomBarChart) {
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.enableGridDashedLine(20f, 20f, 0f)
        xAxis.granularity = 1f
        xAxis.textColor = Color.BLACK
        xAxis.textSize = 10f
        xAxis.labelCount = 15
        xAxis.setCenterAxisLabels(false)
    }

    private fun setData(chart: CustomBarChart, list: ArrayList<BarEntry>) {

        val colors: MutableList<Int> = java.util.ArrayList()
        val green = Color.rgb(113, 191, 134)
        val red = Color.rgb(234, 130, 130)
        for (i in list.indices) {
            val d: BarEntry = list[i]
            if (d.y >= 0) colors.add(green) else colors.add(red)
        }

        val dataSet = BarDataSet(list, "Tổng chi tiêu")
        dataSet.colors = colors
        dataSet.setDrawValues(false)
        dataSet.setValueTextColors(colors)

        val data = BarData(dataSet)
        data.barWidth = 0.9f
        chart.data = data
        chart.invalidate()
    }

}