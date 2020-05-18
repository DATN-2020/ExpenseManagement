package datn.datn_expansemanagement.screen.add_category.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class CategoryDataIntent(
    var id: Int,
    var name: String
) : ViewModel, Parcelable