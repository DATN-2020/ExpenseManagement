package datn.datn_expansemanagement.screen.main_plan.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class PlanItemViewModel(
    var name: String,
    var type: Type
) : ViewModel, Parcelable {
    enum class Type(value: Int) {
        BUDGET(1), TRANSACTION(2), BILL(3)
    }
}