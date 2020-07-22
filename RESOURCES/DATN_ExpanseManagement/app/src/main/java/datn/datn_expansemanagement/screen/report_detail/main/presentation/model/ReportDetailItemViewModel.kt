package datn.datn_expansemanagement.screen.report_detail.main.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ReportDetailItemViewModel(
    var imgUrl: String? = null,
    var name: String? = null,
    var date: String? = null,
    var des: String? = null,
    var price: Double? = null,
    var isLast: Boolean = false
) : ViewModel