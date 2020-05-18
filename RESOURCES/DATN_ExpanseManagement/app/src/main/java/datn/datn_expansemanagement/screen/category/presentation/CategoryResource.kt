package datn.datn_expansemanagement.screen.category.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class CategoryResource : AndroidResourceProvider(){
    fun getTextDonate(): String{
        return resourceManager.getString(R.string.text_donate)
    }

    fun getTextReceive(): String{
        return resourceManager.getString(R.string.text_receive)
    }

    fun getTextLoan(): String{
        return resourceManager.getString(R.string.text_loan)
    }

    fun getTextInvest(): String{
        return resourceManager.getString(R.string.text_invest)
    }

    fun getTitleCategory(): String{
        return resourceManager.getString(R.string.text_choose_category)
    }
}