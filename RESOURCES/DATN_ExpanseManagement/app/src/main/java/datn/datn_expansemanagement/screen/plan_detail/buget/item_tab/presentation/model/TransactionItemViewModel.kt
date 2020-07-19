package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class TransactionItemViewModel (
    var id: Int,
    var idWallet: Int,
    var name: String,
    var imgUrl: String,
    var price: Double,
    var startDate: String,
    var currentDate: String,
    var endDate: String,
    var isFinish: Boolean = false
): ViewModel