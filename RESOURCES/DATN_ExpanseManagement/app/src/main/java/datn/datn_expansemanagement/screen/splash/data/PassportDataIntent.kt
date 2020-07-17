package datn.datn_expansemanagement.screen.splash.data

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class PassportDataIntent (
    val checkWallet: Boolean,
    val fullName: String,
    val password: String? = null,
    val userId: Int,
    val userName: String
): Parcelable, ViewModel