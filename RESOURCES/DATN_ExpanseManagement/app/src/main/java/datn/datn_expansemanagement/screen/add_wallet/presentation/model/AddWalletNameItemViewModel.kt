package datn.datn_expansemanagement.screen.add_wallet.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class AddWalletNameItemViewModel(
    var name: String? = null,
    var type: Type = Type.ACCOUNT_NAME
) : ViewModel{
    enum class Type{
        ACCOUNT_NAME, PAY_TO
    }
}