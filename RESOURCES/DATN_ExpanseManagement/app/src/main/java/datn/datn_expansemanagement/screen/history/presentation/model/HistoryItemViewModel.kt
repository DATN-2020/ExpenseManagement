package datn.datn_expansemanagement.screen.history.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class HistoryItemViewModel (
    var categoryUrl: String? = null,
    var title: String,
    var money: Double,
    var wallet: String,
    var isIncome: Boolean = false
): ViewModel