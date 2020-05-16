@file:Suppress("DEPRECATION")

package datn.datn_expansemanagement.core.app.util

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

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
            var originalString = value.toString()
            val longVar: Long
            if (originalString.contains(",")) {
                originalString = originalString.replace(",".toRegex(), "")
            }
            longVar = originalString.toLong()
            val formatter: DecimalFormat =
                NumberFormat.getInstance(Locale.US) as DecimalFormat
            formatter.applyPattern("#,###,###,###")
            return formatter.format(longVar)
        }
    }

}