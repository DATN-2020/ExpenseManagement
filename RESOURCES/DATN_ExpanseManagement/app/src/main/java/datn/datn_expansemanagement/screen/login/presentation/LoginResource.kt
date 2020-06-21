package datn.datn_expansemanagement.screen.login.presentation

import android.graphics.drawable.Drawable
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class LoginResource : AndroidResourceProvider(){
    fun getTextErrorEmpty(): String {
        return resourceManager.getString(R.string.text_error_empty)
    }
    fun getWarningPhone(): String {
        return resourceManager.getString(R.string.text_warning_phone)
    }

    fun getEditError(): Drawable?{
        return resourceManager.getDrawable(R.drawable.bg_corner_red)
    }

    fun getEditDefault(): Drawable?{
        return resourceManager.getDrawable(R.drawable.bg_corner_e8e8e8)
    }
}