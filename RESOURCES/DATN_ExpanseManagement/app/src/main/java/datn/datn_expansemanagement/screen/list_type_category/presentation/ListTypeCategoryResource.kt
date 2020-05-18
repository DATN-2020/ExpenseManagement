package datn.datn_expansemanagement.screen.list_type_category.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ListTypeCategoryResource : AndroidResourceProvider(){
    fun getTitleTypeCategory(): String{
        return resourceManager.getString(R.string.text_title_category)
    }
}