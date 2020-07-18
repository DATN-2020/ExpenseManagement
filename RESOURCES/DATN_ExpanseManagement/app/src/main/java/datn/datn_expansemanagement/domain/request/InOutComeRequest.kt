package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class InOutComeRequest(
    @SerializedName("Amount")
    val amount: Double? = null,
    @SerializedName("CategoryId_Cate")
    val categoryIdCate: String? = null,
    @SerializedName("Date_come")
    val dateCome: String? = null,
    @SerializedName("Description_come")
    val descriptionCome: String? = null,
    @SerializedName("Id_Bill")
    val idBill: String? = null,
    @SerializedName("Id_Budget")
    val idBudget: String? = null,
    @SerializedName("Id_Per")
    val idPer: String? = null,
    @SerializedName("Id_type")
    val idType: String? = null,
    @SerializedName("LoanId_Loan")
    val loanIdLoan: String? = null,
    @SerializedName("TripId_Trip ")
    val tripIdTrip: String? = null,
    @SerializedName("WalletId_Wallet")
    val walletIdWallet: String? = null
)