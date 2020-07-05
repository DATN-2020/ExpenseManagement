package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


data class GetItemWalletResponse(
    @SerializedName("amount_Wallet")
    val amountWallet: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_Type_Wallet")
    val idTypeWallet: Int,
    @SerializedName("id_Wallet")
    val idWallet: Int,
    @SerializedName("income_Outcomes")
    val incomeOutcomes: Any,
    @SerializedName("name_Wallet")
    val nameWallet: String,
    @SerializedName("user_Id")
    val userId: Int
)