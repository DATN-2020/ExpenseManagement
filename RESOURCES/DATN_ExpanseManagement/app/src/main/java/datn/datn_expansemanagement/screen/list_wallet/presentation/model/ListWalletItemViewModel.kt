package datn.datn_expansemanagement.screen.list_wallet.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ListWalletItemViewModel (
    var id: Int,
    var name: String,
    var totalMoney: Double,
    var isLast: Boolean = false,
    var isChoose: Boolean = false
): ViewModel, Parcelable