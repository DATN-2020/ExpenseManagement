package datn.datn_expansemanagement.screen.account.item_account.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class WalletViewModel(
    var id: Int,
    var tabId: Int,
    var name: String,
    var money: Double,
    var isLast: Boolean = false
): ViewModel, Parcelable