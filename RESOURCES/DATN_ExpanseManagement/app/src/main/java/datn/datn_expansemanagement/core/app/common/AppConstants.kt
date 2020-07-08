package datn.datn_expansemanagement.core.app.common

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.screen.App

interface AppConstants {
    companion object{
        val MONTH_IN_YEAR: Array<String>
            get() = App.app?.resources!!.getStringArray(R.array.list_month_in_year)

        val MONTH_31_DAYS: Array<String>
            get() = App.app?.resources!!.getStringArray(R.array.list_month_31_days)

        val MONTH_30_DAYS: Array<String>
            get() = App.app?.resources!!.getStringArray(R.array.list_month_30_days)
    }
}