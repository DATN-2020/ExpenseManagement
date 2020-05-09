package datn.datn_expansemanagement.screen.main.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class MainResource : AndroidResourceProvider(){
    fun getColorBackground(): Int{
        return resourceManager.getColor(R.color.color_219dfd)
    }
}