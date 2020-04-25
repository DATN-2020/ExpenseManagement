package datn.datn_expansemanagement.screen.account.item_account.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ItemAccountAccumulationViewModel (
    var id: Int,
    var name: String,
    var moneyAccum: Double,
    var moneyCurrent: Double,
    var moneyRest: Double,
    var isLast: Boolean
): ViewModel