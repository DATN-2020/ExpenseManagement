package datn.datn_expansemanagement.screen.add_expanse.presentation

import android.graphics.drawable.Drawable
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AddExpenseResource : AndroidResourceProvider(){

    fun getColorStatusBar(): Int {
        return resourceManager.getColor(R.color.color_status_bar_opacity)
    }

    fun getIconReceive(): Drawable?{
        return resourceManager.getDrawable(R.drawable.ic_receive)
    }

    fun getIconSubtract(): Drawable?{
        return resourceManager.getDrawable(R.drawable.ic_subtract)
    }

    fun getTextReceive(): String{
        return resourceManager.getString(R.string.text_receive)
    }

    fun getTextDonate(): String{
        return resourceManager.getString(R.string.text_donate)
    }

    fun getColorSelected(): Int{
        return resourceManager.getColor(R.color.color_219dfd)
    }

    fun getColorUnSelected(): Int{
        return resourceManager.getColor(R.color.black)
    }

}