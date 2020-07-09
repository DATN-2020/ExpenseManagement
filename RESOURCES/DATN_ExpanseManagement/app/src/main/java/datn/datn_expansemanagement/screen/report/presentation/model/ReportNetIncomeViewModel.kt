package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportNetIncomeViewModel(
    var priceNetIncome: Double = 0.0 // = tổng thu nhập - tổng chi tiêu (tính từ thời điểm bắt đầu xét tới thời điểm kết thúc xét)
) : ViewModel