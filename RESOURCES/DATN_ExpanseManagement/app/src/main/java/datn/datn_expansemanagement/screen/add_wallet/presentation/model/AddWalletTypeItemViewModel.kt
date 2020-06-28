package datn.datn_expansemanagement.screen.add_wallet.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class AddWalletTypeItemViewModel(
    var id: Int? = null,
    var name : String? = null,
    var hint: String,
    var type: Type? = Type.DEFAULT
) : ViewModel{
    enum class Type{
        DEFAULT, SAVING, START_DATE, PERIOD
    }
}