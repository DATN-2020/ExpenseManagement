package datn.datn_expansemanagement.screen.splash.data

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class PassportDataIntent (
    var id: Int,
    var phone: String,
    var name: String
): Parcelable, ViewModel