package datn.datn_expansemanagement.screen.add_category.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AddCategoryResource : AndroidResourceProvider(){
    fun getTextErrorEmpty(): String {
        return resourceManager.getString(R.string.text_warning_add_category_title)
    }

    fun getColorError(): Int{
        return resourceManager.getColor(R.color.color_db5a5a)
    }

    fun getColorSuccess(): Int{
        return resourceManager.getColor(R.color.black)
    }
}