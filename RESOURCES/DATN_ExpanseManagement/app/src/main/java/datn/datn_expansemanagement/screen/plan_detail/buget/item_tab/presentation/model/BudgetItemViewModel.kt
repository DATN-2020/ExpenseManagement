package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class BudgetItemViewModel (
    var id: Int,
    var name: String,
    var totalPrice: Double,
    var currentPrice: Double
): ViewModel