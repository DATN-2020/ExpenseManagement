package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class UpdateWalletRequest(
    @SerializedName("Amount_Wallet")
    val amountWallet: Double,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Name_Wallet")
    val nameWallet: String
)