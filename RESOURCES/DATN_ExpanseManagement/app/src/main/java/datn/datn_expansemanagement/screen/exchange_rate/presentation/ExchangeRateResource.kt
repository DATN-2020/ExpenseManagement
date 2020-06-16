package datn.datn_expansemanagement.screen.exchange_rate.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ExchangeRateResource : AndroidResourceProvider(){
    fun getTextTitle(): String{
        return resourceManager.getString(R.string.text_exchange_rate)
    }
    fun getHintTitle(): String{
        return resourceManager.getString(R.string.text_hint_search_rate)
    }
}