package datn.datn_expansemanagement.screen.add_wallet.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class AddWalletRateItemViewModel(
    var rate: Double? = null,
    var title: String,
    var unit: String,
    var price: Double = 0.0,
    var isResult: Boolean = false
) : ViewModel