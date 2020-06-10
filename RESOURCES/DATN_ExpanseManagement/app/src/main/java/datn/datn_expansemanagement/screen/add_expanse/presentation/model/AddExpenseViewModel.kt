package datn.datn_expansemanagement.screen.add_expanse.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class AddExpenseViewModel(
    var type: Type? = Type.DONATE,
    var isChoose: Boolean = false,
    var isLast: Boolean = false,
    var info: Info? = null
) : ViewModel {

    enum class Type {
        DONATE, RECEIVE, LOAN, INVEST
    }

    class Info(
        var totalMoney: Double = 0.0,
        var category: Category? = null,
        var title: String? = null,
        var date: String? = null,
        var time: String? = null,
        var wallet: Wallet? = null,
        var trip: Trip? = null,
        var listFriend: ListFriend? = null,
        var location: Location? = null

    ) : ViewModel {
        class Category(
            var id: Int? = null,
            var name: String? = null,
            var isChoose: Boolean = false
        ) : ViewModel

        class Wallet(
            var id: Int? = null,
            var name: String? = null,
            var isChoose: Boolean = false
        ) : ViewModel

        class Trip(
            var id: Int? = null,
            var name: String? = null
        ) : ViewModel

        class ListFriend(
            var list: MutableList<ViewModel> = mutableListOf()
        ) : ViewModel {
            class Friend(
                var id: Int? = null,
                var name: String? = null
            ) : ViewModel
        }

        class Location(
            var id: Int? = null,
            var name: String? = null
        ) : ViewModel
    }
}