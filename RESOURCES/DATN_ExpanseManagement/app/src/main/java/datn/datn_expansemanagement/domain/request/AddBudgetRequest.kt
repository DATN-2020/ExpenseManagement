package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class AddBudgetRequest(
    @SerializedName("Amount_Budget")
    val amountBudget: Double,
    @SerializedName("Id_Cate")
    val idCate: String? = null,
    @SerializedName("Id_Type")
    val idType: String? = null,
    @SerializedName("Id_Wallet")
    val idWallet: String,
    @SerializedName("time_e")
    val timeE: String,
    @SerializedName("time_s")
    val timeS: String,
    @SerializedName("id_Time")
    val idTime: String
)