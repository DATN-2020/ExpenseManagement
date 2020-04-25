package datn.datn_expansemanagement.screen.overview.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

// tạo model làm sao để m hiển thị được y chan app người ta là được
class ExchangeRateViewModel (
    var id: Int,
    var name : String,
    var exchange: String,
    var imageurl: String,
    var isChoose: Boolean? = false
): ViewModel