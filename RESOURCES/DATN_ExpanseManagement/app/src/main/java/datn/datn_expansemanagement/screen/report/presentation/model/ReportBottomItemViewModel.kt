package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportBottomItemViewModel(
    var priceDue: Double = 0.0,
    var priceBorrow: Double = 0.0,
    var priceOther: Double = 0.0
) : ViewModel