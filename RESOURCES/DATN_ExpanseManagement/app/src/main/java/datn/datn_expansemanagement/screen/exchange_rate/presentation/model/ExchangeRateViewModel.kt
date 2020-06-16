package datn.datn_expansemanagement.screen.exchange_rate.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ExchangeRateViewModel (
    var id: String,
    var moneyBuy : String,
    var moneySell : String,
    var imageurl: String,
    var isChoose: Boolean? = false
): ViewModel