package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportHeaderCardViewModel (
    var price: Double,
    var startDate: String,
    var endDate: String,
    var isFinish: Boolean = false
): ViewModel