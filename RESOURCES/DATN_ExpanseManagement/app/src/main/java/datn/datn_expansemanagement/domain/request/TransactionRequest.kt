package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class TransactionRequest(
    @SerializedName("Amount_Per")
    val amountPer: Double,
    @SerializedName("date_e")
    val dateE: String,
    @SerializedName("date_s")
    val dateS: String,
    @SerializedName("Desciption")
    val desciption: String,
    @SerializedName("Id_Cate")
    val idCate: String?,
    @SerializedName("id_Time")
    val idTime: String?,
    @SerializedName("Id_Type")
    val idType: String?,
    @SerializedName("Id_Wallet")
    val idWallet: String?
)