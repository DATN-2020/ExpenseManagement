package datn.datn_expansemanagement.screen.account.item_account.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ItemAccountAccumulationViewModel (
    var id: Int,
    var name: String,
    var moneyAccumulation: Double,
    var moneyCurrent: Double,
    var isLast: Boolean = false
): ViewModel