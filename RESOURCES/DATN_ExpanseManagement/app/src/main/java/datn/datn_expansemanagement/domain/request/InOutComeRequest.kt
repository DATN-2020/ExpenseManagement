package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class InOutComeRequest(
    @SerializedName("Amount")
    val amount: Double,
    @SerializedName("CategogyId_Cate")
    val categogyIdCate: Int,
    @SerializedName("Date_come")
    val dateCome: String,
    @SerializedName("Description_come")
    val descriptionCome: String,
    @SerializedName("Is_come")
    val isCome: Int,
    @SerializedName("LoanId_Loan")
    val loanIdLoan: Int,
    @SerializedName("TripId_Trip")
    val tripIdTrip: Int,
    @SerializedName("TypeCategoryId")
    val typeCategoryId: Int?= 0,
    @SerializedName("WalletId_Wallet")
    val walletIdWallet: Int
)