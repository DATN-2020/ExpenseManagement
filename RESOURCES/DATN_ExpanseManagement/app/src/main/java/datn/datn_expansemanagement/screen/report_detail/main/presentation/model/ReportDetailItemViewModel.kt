package datn.datn_expansemanagement.screen.report_detail.main.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportDetailItemViewModel(
    var imgUrl: String? = null,
    var name: String,
    var date: String,
    var des: String,
    var price: Double,
    var isLast: Boolean = false
) : ViewModel