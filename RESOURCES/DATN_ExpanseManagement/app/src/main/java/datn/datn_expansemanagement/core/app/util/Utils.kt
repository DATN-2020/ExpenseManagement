@file:Suppress("DEPRECATION")

package datn.datn_expansemanagement.core.app.util

import android.text.SpannableString
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.widget.TextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Utils {
    companion object {

        @JvmStatic
        fun setUnderlineForTextView(textView: TextView) {
            val spannableString = SpannableString(textView.text)
            spannableString.setSpan(UnderlineSpan(), 0, textView.length(), 0)
            textView.text = spannableString
        }

        @JvmStatic
        fun formatMoneyVND(value: Double): String {
            val myFormatter = DecimalFormat("###,###,###", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value).plus(" đ")
        }

        fun formatMoney(value: Double): String {
            val myFormatter = DecimalFormat("###,###,###", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value)
        }

        fun customFormatMoney(value: String): String {
            var originalString = value
            var longVar: Long? = 0

            if (originalString.contains(".")) {
                originalString = originalString.replace(".".toRegex(), "")
            }

            if (originalString.contains(",")) {
                originalString = originalString.replace(",".toRegex(), "")
            }
            if(!originalString.isNullOrEmpty()){
                longVar = originalString.toLong()
            }
            val formatter: DecimalFormat =
                NumberFormat.getInstance(Locale.US) as DecimalFormat
            formatter.applyPattern("#,###,###,###")
            return formatter.format(longVar)
        }

        @JvmStatic
        fun checkValidPhoneNumber(phone: String): Boolean {
            return if (TextUtils.isEmpty(phone)) {
                false
            } else {
                Pattern.matches("0+[0-9]{9}", phone)
            }

        }

        @JvmStatic
        fun convertDateFormat(
            inputDate: String,
            fromFormat: SimpleDateFormat,
            toFormat : SimpleDateFormat
        ): String {
            if (inputDate.isEmpty()) return ""
            return try {
                val toDate = fromFormat.parse(inputDate)
                toFormat.format(toDate)
            } catch (ex: java.lang.Exception) {
                ""
            }
        }
    }

}