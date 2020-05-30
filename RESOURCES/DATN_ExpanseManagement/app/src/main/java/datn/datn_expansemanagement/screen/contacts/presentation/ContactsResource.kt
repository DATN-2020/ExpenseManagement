package datn.datn_expansemanagement.screen.contacts.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ContactsResource : AndroidResourceProvider(){
    fun getColorBlack(): Int{
        return resourceManager.getColor(R.color.black)
    }

    fun getColorRed(): Int{
        return resourceManager.getColor(R.color.color_ee403f)
    }
}