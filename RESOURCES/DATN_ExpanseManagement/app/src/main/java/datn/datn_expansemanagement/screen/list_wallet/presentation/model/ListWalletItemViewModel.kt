package datn.datn_expansemanagement.screen.list_wallet.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ListWalletItemViewModel (
    var id: Int,
    var name: String,
    var totalMoney: Double,
    var isLast: Boolean = false,
    var isChoose: Boolean = false
): ViewModel