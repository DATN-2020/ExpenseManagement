package datn.datn_expansemanagement.screen.report_detail.main.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class ReportDetailResource : AndroidResourceProvider(){
    fun getColorChart(): Int{
        return resourceManager.getColor(R.color.color_219dfd)
    }

    fun getTextChartColor(): Int{
        return resourceManager.getColor(R.color.color_399b54)
    }

    fun getBackgroundChart(): Int{
        return resourceManager.getColor(R.color.white)
    }
}