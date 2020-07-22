package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.mikephil.charting.data.PieEntry
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportPieChartViewModel(
    var list : ArrayList<PieEntry>,
    var isCreditCard: Boolean = false
) : ViewModel