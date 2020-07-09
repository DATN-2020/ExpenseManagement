package datn.datn_expansemanagement.screen.account.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class TabItemViewModel(
    var id: Int,
    var name: String,
    var typePlanId: PlanItemViewModel.Type? = null
) : ViewModel, Parcelable