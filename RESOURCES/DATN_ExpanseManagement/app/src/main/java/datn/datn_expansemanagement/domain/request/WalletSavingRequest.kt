package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class WalletSavingRequest(
    @SerializedName("date_e")
    val dateE: String,
    @SerializedName("date_s")
    val dateS: String,
    @SerializedName("id_bank")
    val idBank: Int,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("name_saving")
    val nameSaving: String,
    @SerializedName("price")
    val price: Double
)