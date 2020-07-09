package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class GetWalletItemViewModel (
    var id: Int,
    var name: String,
    var money: Double = 0.0,
    var isChoose: Boolean = false
): ViewModel