package datn.datn_expansemanagement.screen.list_wallet.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ListWalletResource : AndroidResourceProvider(){
    fun getTextTitleScreen(): String{
        return resourceManager.getString(R.string.text_choose_wallet)
    }
}