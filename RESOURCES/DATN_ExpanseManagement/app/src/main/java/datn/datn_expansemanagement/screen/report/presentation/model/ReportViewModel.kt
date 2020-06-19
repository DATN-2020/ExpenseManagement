package datn.datn_expansemanagement.screen.report.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ReportViewModel(
    var name: String,
    var type: TypeReport
) : ViewModel, Parcelable {
    enum class TypeReport {
        FINANCE, RECEIVE, DONATE, FRIEND, LOAN, TRIP
    }
}