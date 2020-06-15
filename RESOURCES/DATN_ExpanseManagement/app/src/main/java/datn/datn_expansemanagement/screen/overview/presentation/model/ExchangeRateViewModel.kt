package datn.datn_expansemanagement.screen.overview.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ExchangeRateViewModel (
    var id: Int,
    var name : String,
    var exchange: String,
    var imageurl: String,
    var isChoose: Boolean? = false
): ViewModel