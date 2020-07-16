package datn.datn_expansemanagement.screen.add_plan.presentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AddPlanResource : AndroidResourceProvider(){
    fun getDateCheckFormat(firstParams: String, secondParams: String):String{
        return String.format(resourceManager.getString(R.string.text_date_check_format),firstParams,secondParams)
    }

    fun getDay(dayInWeek: Int): String{
        return when(dayInWeek){
            1 -> resourceManager.getString(R.string.text_sunday)
            2 -> resourceManager.getString(R.string.text_monday)
            3 -> resourceManager.getString(R.string.text_tuesday)
            4 -> resourceManager.getString(R.string.text_wednesday)
            5 -> resourceManager.getString(R.string.text_thursday)
            6 -> resourceManager.getString(R.string.text_friday)
            7 -> resourceManager.getString(R.string.text_saturday)
            else -> "Error"
        }
    }

    fun getDateTotal(dayDiff: Int): String{
        return String.format(resourceManager.getString(R.string.text_total_date),dayDiff)
    }

    fun getDateCheckIn(): String{
        return resourceManager.getString(R.string.text_date_checkin)
    }

    fun getDateCheckOut(): String{
        return resourceManager.getString(R.string.text_date_checkout)
    }
}