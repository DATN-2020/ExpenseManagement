package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


class TypeWalletResponse(
    @SerializedName("id_Type_Wallet")
    val idTypeWallet: Int,
    @SerializedName("image_Type_Wallet")
    val imageTypeWallet: String,
    @SerializedName("name_Type_Wallet")
    val nameTypeWallet: String,
    @SerializedName("wallets")
    val wallets: String? = null
)