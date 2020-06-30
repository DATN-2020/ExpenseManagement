package datn.datn_expansemanagement.screen.splash.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class SplashResource : AndroidResourceProvider(){
    fun getTitleSplash1(): String{
        return resourceManager.getString(R.string.text_splash_1)
    }

    fun getTitleSplash2(): String{
        return resourceManager.getString(R.string.text_splash_2)
    }

    fun getTitleSplash3(): String{
        return resourceManager.getString(R.string.text_splash_3)
    }

    fun getColorStatusBar(): Int {
        return resourceManager.getColor(R.color.color_status_bar_opacity)
    }
}