package datn.datn_expansemanagement.screen.history.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class HistoryResource : AndroidResourceProvider(){
    fun getColorIncome(): Int{
        return resourceManager.getColor(R.color.color_219dfd)
    }
    fun getColorOutcome(): Int{
        return resourceManager.getColor(R.color.color_ee403f)
    }
}