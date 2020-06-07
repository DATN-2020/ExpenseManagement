package datn.datn_expansemanagement.screen.history.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class HistoryDateItemViewModel (
    var numberDay: Int,
    var dayOfWeek: String,
    var month: String,
    var inCome: Double? = null,
    var outCome: Double?= null
): ViewModel