package datn.datn_expansemanagement.screen.login.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class LoginResource : AndroidResourceProvider(){
    fun getTextErrorEmpty(): String {
        return resourceManager.getString(R.string.text_warning_add_category_title)
    }
}