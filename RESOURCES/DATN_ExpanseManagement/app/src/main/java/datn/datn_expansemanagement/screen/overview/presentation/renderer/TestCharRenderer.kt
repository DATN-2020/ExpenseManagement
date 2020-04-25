package datn.datn_expansemanagement.screen.overview.presentation.renderer

import android.content.Context
import android.graphics.Color
import android.view.View
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.model.TestChart
import kotlinx.android.synthetic.main.item_chart_column.view.*

class TestCharRenderer (context: Context): ViewRenderer<TestChart>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_chart_column
    }

    override fun getModelClass(): Class<TestChart> = TestChart::class.java

    override fun bindView(model: TestChart, viewRoot: View) {
        val barDataSet = BarDataSet(model.listBarEntry, "Test")
        viewRoot.chartsTest.animateY(1000)
        val barData = BarData(barDataSet)
        val listColor = mutableListOf<Int>()
        listColor.add(Color.rgb(113, 191, 134)) // green
        listColor.add(Color.rgb(234, 130, 130)) // red
        barDataSet.colors = listColor
        barData.barWidth = 60f
        viewRoot.chartsTest.fitScreen()
        viewRoot.chartsTest.enableScroll()
        viewRoot.chartsTest.data = barData
        viewRoot.chartsTest.invalidate()
    }

}