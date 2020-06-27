package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


class WalletResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id_Wallet")
    val idWallet: Int,
    @SerializedName("income_Outcomes")
    val incomeOutcomes: Any,
    @SerializedName("name_Wallet")
    val nameWallet: String
)

