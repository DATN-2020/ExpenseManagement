package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class PutInWalletSavingRequest(
    @SerializedName("date_trans")
    val dateTrans: String,
    @SerializedName("id_saving")
    val idSaving: Int,
    @SerializedName("is_Income")
    val isIncome: Boolean,
    @SerializedName("name_trans")
    val nameTrans: String,
    @SerializedName("price_trans")
    val priceTrans: Double
)