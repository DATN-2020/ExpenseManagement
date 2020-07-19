package datn.datn_expansemanagement.screen.account.item_account.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ItemAccountAccumulationViewModel (
    var id: Int,
    var name: String,
    var startDate: String,
    var endDate : String,
    var price: Double,
    var isLast: Boolean = false
): ViewModel, Parcelable