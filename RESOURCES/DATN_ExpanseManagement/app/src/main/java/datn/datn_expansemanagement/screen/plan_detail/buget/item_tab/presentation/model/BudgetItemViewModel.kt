package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class BudgetItemViewModel (
    var id: Int,
    var name: String,
    var imgUrl: String,
    var totalPrice: Double,
    var currentPrice: Double,
    var idWallet: Int,
    var isFinish: Boolean = false
): ViewModel