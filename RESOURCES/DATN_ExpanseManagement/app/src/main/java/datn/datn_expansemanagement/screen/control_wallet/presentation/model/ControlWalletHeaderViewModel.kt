package datn.datn_expansemanagement.screen.control_wallet.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ControlWalletHeaderViewModel(
    var nameWallet: String,
    var price : Double,
    var title: String,
    var isOtherWallet: Boolean = false
) : ViewModel