package datn.datn_expansemanagement.screen.account.item_account.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ItemAccountResource : AndroidResourceProvider(){
    fun getColorName(): Int{
        return resourceManager.getColor(R.color.black)
    }
}