package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.content.Context
import android.view.View
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.report.presentation.ReportResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportPieChartViewModel
import kotlinx.android.synthetic.main.item_layout_report_pie_chart.view.*

class ReportPieChartViewRenderer(
    context: Context, private val mResource: ReportResource,
    private val onActionChart: OnActionNotify
) : ViewRenderer<ReportPieChartViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_pie_chart
    }

    override fun getModelClass(): Class<ReportPieChartViewModel> =
        ReportPieChartViewModel::class.java

    override fun bindView(model: ReportPieChartViewModel, viewRoot: View) {
        val chart = viewRoot.pieChart
        chart.clear()
        chart.description.isEnabled = false
        chart.setBackgroundColor(mResource.getBackgroundChart())
        chart.enableScroll()
        chart.setUsePercentValues(true)
        chart.setExtraOffsets(5f, 10f, 5f, 5f)
        chart.dragDecelerationFrictionCoef = 0.9f
        chart.animateY(1000)
        chart.setDrawCenterText(true)
//        chart.setDrawRoundedSlices(true) // làm tròn các đầu của chart
        chart.isDrawHoleEnabled = true // khoảng trắng ở giữa chart // false sẽ mất

        // center
        chart.centerText = "Thống kê chi tiêu"
        chart.setCenterTextColor(mResource.getColorChart())
        chart.setCenterTextSize(14f)

//        val mv = CustomMarkerView(context, R.layout.custom_marker_view)
//        mv.chartView = chart
//        chart.marker = mv

        chart.animate()

        if (!model.isCreditCard) {
            chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onNothingSelected() {

                }

                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    // next activity
                    EventFireUtil.fireEvent(onActionChart)
                }

            })
        }


        setData(chart, model.list)
        setLegend(chart)
    }

    private fun setLegend(chart: PieChart) {
        val legend = chart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled = false
    }

    private fun setData(chart: PieChart, list: ArrayList<PieEntry>) {
        val dataSet = PieDataSet(list, "")
        dataSet.color = mResource.getColorChart()
        dataSet.valueTextColor = mResource.getBackgroundChart()
        dataSet.valueTextSize = 10f
        dataSet.sliceSpace = 4f
        dataSet.selectionShift = 4f

        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f


        val listColor = mutableListOf<Int>()
        listColor.add(mResource.getTextChartColor())
        listColor.add(mResource.getColorDonate())
        dataSet.colors = listColor

        val data = PieData(dataSet)
        chart.data = data
        chart.invalidate()
    }

}