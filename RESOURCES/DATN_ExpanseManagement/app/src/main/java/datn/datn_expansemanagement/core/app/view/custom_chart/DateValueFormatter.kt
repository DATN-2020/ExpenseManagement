package datn.datn_expansemanagement.core.app.view.custom_chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class DateValueFormatter : ValueFormatter() {

    private val months = arrayOf(1, 2, 3, 4, 5, 6 ,7 ,8 ,9 ,10, 11, 12)

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return super.getFormattedValue(value, axis)
    }
}