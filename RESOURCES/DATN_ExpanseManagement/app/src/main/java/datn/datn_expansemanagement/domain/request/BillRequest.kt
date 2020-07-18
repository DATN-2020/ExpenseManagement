package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class BillRequest(
    @SerializedName("Amount_Bill")
    val amountBill: Double,
    @SerializedName("date_e")
    val dateE: String,
    @SerializedName("date_s")
    val dateS: String,
    @SerializedName("Desciption")
    val desciption: String,
    @SerializedName("Id_Category")
    val idCategory: String?,
    @SerializedName("id_Time")
    val idTime: String,
    @SerializedName("Id_Type")
    val idType: String?,
    @SerializedName("Id_Wallet")
    val idWallet: String
)