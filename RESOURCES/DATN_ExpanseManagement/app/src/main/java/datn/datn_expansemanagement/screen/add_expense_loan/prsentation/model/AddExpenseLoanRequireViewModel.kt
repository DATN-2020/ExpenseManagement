package datn.datn_expansemanagement.screen.add_expense_loan.prsentation.model

import android.os.Parcelable
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddExpenseLoanRequireViewModel(
        var nameLoaner: String? = null,
        var idWallet: Int? = null,
        var nameWallet: String? = null,
        var date: String? = null,
        var time: String? = null,
        var isLoan: Boolean = false
) : ViewModel, Parcelable