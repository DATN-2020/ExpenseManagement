@file:Suppress("DEPRECATION")
package datn.datn_expansemanagement.core.app.util

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView

class Utils {
    companion object {

        @JvmStatic
        fun setUnderlineForTextView(textView: TextView) {
            val spannableString = SpannableString(textView.text)
            spannableString.setSpan(UnderlineSpan(), 0, textView.length(), 0)
            textView.text = spannableString
        }
    }

}