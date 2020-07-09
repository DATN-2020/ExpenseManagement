package datn.datn_expansemanagement.screen.report_detail.main.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportDetailDayViewModel(
    var numberDay: Int,
    var dayOfWeek: String,
    var month: String,
    var inCome: Double = 0.0,
    var outCome: Double= 0.0
) : ViewModel