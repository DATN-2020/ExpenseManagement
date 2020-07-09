package datn.datn_expansemanagement.screen.plan_detail.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class PlanDetailResource : AndroidResourceProvider() {
    fun getColorOutMax(): Int {
        return resourceManager.getColor(R.color.color_ee403f)
    }

    fun getColorNormal(): Int {
        return resourceManager.getColor(R.color.color_51c471)
    }

    fun getColorLow(): Int {
        return resourceManager.getColor(R.color.color_219dfd)
    }
}