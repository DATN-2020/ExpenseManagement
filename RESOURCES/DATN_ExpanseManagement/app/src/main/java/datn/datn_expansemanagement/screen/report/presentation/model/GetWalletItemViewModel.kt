package datn.datn_expansemanagement.screen.report.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class GetWalletItemViewModel (
    var id: Int,
    var name: String,
    var money: Double = 0.0,
    var currentMoney: Double =0.0,
    var isChoose: Boolean = false,
    var isCreditCard: Boolean = false,
    var date: String? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var interest: Double? = null,
    var isFinish: Boolean = false
): ViewModel