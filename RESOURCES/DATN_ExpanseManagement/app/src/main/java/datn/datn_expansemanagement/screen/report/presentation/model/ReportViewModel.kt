package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportViewModel(
    var name: String,
    var type: TypeReport
) : ViewModel {
    enum class TypeReport {
        FINANCE, RECEIVE, DONATE, FRIEND, LOAN, TRIP
    }
}