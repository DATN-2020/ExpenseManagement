package datn.datn_expansemanagement.view.custom_charts

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import datn.datn_expansemanagement.R
import de.hdodenhof.circleimageview.CircleImageView

class CustomMarkerView(context: Context, layoutResource: Int/*, val listData: List<Data>*/): MarkerView(context, layoutResource){
    private var iconCategory : CircleImageView? = null
    private var offY = 0f
    private var offX = 0f
    init {
        iconCategory = findViewById(R.id.imgIcon)
    }

    override fun refreshContent(e: Entry, highlight: Highlight?) {
        super.refreshContent(e, highlight)
    }
}