package datn.datn_expansemanagement.screen.category.item_category.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class ItemCategoryViewModel(
    var id: Int,
    var name: String,
    var isChoose: Boolean = false
) : ViewModel, Parcelable