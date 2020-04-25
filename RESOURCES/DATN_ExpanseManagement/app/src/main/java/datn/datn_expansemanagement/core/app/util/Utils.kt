@file:Suppress("DEPRECATION")
package datn.datn_expansemanagement.core.app.util

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
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

    }

}