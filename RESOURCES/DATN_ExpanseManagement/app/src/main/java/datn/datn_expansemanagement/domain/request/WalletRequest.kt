package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName

data class WalletRequest(
    @SerializedName("Amount")
    val amountWallet: Double,
    @SerializedName("Description")
    val description: String? = null,
    @SerializedName("Id_Type_Wallet")
    val idTypeWallet: Int? = null,
    @SerializedName("Name_Wallet")
    val nameWallet: String? = null,
    @SerializedName("User_Id")
    val userId: Int
)