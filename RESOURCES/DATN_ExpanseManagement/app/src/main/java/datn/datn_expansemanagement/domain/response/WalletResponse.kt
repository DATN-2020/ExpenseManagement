package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


data class WalletResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
)

data class Data(
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