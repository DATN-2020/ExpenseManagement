package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportHeaderItemViewModel (
    var beginBalance: Double, // = tổng thu nhập - tổng chi tiêu (tính tới thời điểm bắt đầu xét)
    var endBalance: Double // = tổng thu nhập - tổng chi tiêu (tính tới thời điểm kết thúc xét )
): ViewModel