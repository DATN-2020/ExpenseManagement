package datn.datn_expansemanagement.screen.account.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AccountResource:AndroidResourceProvider(){
    fun getTabOne(): String{
        return resourceManager.getString(R.string.text_wallet_money)
    }

    fun getTabTwo(): String{
        return resourceManager.getString(R.string.text_card)
    }
}