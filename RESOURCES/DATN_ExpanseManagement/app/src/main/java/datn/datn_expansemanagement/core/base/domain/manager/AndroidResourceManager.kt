package datn.datn_expansemanagement.core.base.domain.manager

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import datn.datn_expansemanagement.core.app.common.AppConfigs


class AndroidResourceManager : ResourceManager {
    override fun getString(@StringRes stringId: Int): String =
        AppConfigs.instance.getBaseApplication().getString(stringId)

    override fun getInt(@IntegerRes intId: Int): Int =
        AppConfigs.instance.getBaseApplication().resources?.getInteger(intId)!!

    override fun getDrawable(@DrawableRes drawableId: Int): Drawable? = ContextCompat.getDrawable(
        AppConfigs.instance.getBaseApplication(), drawableId
    )

    @ColorInt
    override fun getColor(@ColorRes colorId: Int): Int =
        ContextCompat.getColor(AppConfigs.instance.getBaseApplication(), colorId)
}