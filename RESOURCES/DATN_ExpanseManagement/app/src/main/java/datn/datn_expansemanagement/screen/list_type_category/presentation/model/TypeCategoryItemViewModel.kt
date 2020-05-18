package datn.datn_expansemanagement.screen.list_type_category.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class TypeCategoryItemViewModel(
    var id: Int,
    var name: String,
    var isChoose : Boolean = false,
    var isLast : Boolean = false
) : ViewModel, Parcelable