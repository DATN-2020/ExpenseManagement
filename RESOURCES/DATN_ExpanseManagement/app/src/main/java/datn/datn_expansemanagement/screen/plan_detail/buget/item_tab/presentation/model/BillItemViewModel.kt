package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class BillItemViewModel(
    val amount: Double,
    val dateE: String,
    val dateS: String,
    val currentDate: String,
    val idBill: Int,
    val image: String,
    val isDeadline: Boolean,
    val isPay: Boolean,
    val name: String
) : ViewModel