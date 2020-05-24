package datn.datn_expansemanagement.screen.trip.item_trip.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ItemTripViewModel(var id: Int,
                        var nameIcon: String,
                        var name: String, var isFinished: Boolean = false) : ViewModel, Parcelable