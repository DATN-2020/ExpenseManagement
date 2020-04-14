package datn.datn_expansemanagement.core.base.utils

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import datn.datn_expansemanagement.core.app.common.AppConfigs

class Util {
    class Resource {
        companion object {
            @JvmStatic
            fun getString(@StringRes idStr: Int): String =
                    AppConfigs.instance.getBaseApplication().getString(idStr)

            @JvmStatic
            fun getColor(colorId: Int): Int =
                    ContextCompat.getColor(AppConfigs.instance.getBaseApplication(), colorId)

            @JvmStatic
            fun getDrawable(@DrawableRes drawableId: Int): Drawable {
                return ContextCompat.getDrawable(AppConfigs.instance.getBaseApplication(), drawableId)!!
            }
        }
    }

}