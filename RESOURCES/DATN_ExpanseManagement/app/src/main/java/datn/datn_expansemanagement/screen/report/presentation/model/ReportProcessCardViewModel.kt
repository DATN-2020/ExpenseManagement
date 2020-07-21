package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportProcessCardViewModel (
    var currentPrice: Double,
    var progress: Int,
    var startDate: String? = null,
    var endDate: String? = null
): ViewModel