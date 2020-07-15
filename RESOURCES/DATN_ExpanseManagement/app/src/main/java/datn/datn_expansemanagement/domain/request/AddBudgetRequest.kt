package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class AddBudgetRequest(
    @SerializedName("Amount_Budget")
    var amountBudget: Int,
    @SerializedName("Id_Cate")
    var idCate: Int,
    @SerializedName("Id_Type")
    var idType: Int,
    @SerializedName("Id_Wallet")
    var idWallet: Int,
    @SerializedName("time_e")
    var timeE: String,
    @SerializedName("time_s")
    var timeS: String
)