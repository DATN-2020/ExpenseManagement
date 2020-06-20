package datn.datn_expansemanagement.screen.splash.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class SplashModel(
    var img: Int? = null,
    var title: String? = null
) : ViewModel, Parcelable