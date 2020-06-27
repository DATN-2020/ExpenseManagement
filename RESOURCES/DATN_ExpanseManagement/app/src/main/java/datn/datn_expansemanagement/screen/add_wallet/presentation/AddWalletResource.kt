package datn.datn_expansemanagement.screen.add_wallet.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AddWalletResource : AndroidResourceProvider(){
    fun getTitleAddWalletDefault(): String{
        return resourceManager.getString(R.string.text_add_wallet_default)
    }
    fun getTitleAddWalletSaving(): String{
        return resourceManager.getString(R.string.text_add_wallet_saving)
    }
    fun getTitleAddWalletAccumulation(): String{
        return resourceManager.getString(R.string.text_add_wallet_accumulation)
    }
}