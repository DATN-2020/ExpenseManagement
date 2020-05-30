package datn.datn_expansemanagement.screen.add_expense_donate.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class AddExpenseDonateInfoViewModel (
    var isExpand: Boolean= false,
    var idTrip: Int?=  null,
    var tripName: String? = null,
    var listFriend: MutableList<ItemFriend>? = null
): ViewModel{
    class ItemFriend(
        var idFriend: Int?=  null,
        var friendName: String? = null
    ): ViewModel
}