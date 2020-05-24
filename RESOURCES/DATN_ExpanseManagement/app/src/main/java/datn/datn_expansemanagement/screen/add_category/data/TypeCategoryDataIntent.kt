package datn.datn_expansemanagement.screen.add_category.data

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class TypeCategoryDataIntent(
    var id: Int
) : ViewModel, Parcelable