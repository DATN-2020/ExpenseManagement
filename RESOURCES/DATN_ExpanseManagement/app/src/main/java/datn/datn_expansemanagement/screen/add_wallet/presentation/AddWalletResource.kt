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

    fun getTitleStartPrice(): String{
        return resourceManager.getString(R.string.text_start_price)
    }

    fun getHintTypeWallet(): String{
        return resourceManager.getString(R.string.text_enter_type_wallet)
    }

    fun getHintBank(): String{
        return resourceManager.getString(R.string.text_bank)
    }

    fun getStartDateSent(): String{
        return resourceManager.getString(R.string.text_start_date_sent)
    }

    fun getPeriod(): String{
        return resourceManager.getString(R.string.text_period)
    }
}