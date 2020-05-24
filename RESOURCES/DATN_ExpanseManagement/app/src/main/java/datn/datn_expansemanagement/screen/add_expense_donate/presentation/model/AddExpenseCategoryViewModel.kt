package datn.datn_expansemanagement.screen.add_expense_donate.presentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddExpenseCategoryViewModel (
    var idCategory: Int? = null,
    var nameCategory: String? = null,
    var idWallet: Int? = null,
    var nameWallet: String? = null,
    var date: String? = null,
    var time: String? = null
): ViewModel, Parcelable