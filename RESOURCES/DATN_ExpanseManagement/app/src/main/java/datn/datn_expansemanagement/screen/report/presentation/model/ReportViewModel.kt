package datn.datn_expansemanagement.screen.report.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ReportViewModel(
    var date: String? = null,
    var idWallet: Int? = null,
    var idBudget: Int? = null,
    var idPeriodic: Int? = null,
    var idBill: Int? = null
) : ViewModel, Parcelable