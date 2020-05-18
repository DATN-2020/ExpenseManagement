package datn.datn_expansemanagement.screen.add_expense_donate.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddExpenseDonateCategoryViewModel (
    var id: Int? = null,
    var nameCategory: String? = null
): ViewModel, Parcelable