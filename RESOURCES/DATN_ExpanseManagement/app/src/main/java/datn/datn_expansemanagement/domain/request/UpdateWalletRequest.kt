package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class UpdateWalletRequest(
    @SerializedName("Amount_Wallet")
    val amountWallet: Double,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Id_Type_Wallet")
    val idTypeWallet: Int? = null,
    @SerializedName("Id_Wallet")
    val idWallet: Int? = null,
    @SerializedName("Name_Wallet")
    val nameWallet: String,
    @SerializedName("User_Id")
    val userId: Int
)