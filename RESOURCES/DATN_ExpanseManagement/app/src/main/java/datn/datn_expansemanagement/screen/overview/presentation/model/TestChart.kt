package datn.datn_expansemanagement.screen.overview.presentation.model

import com.github.mikephil.charting.data.BarEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class TestChart (
    var listBarEntry: MutableList<BarEntry>,
    var listYear: MutableList<String>
): ViewModel