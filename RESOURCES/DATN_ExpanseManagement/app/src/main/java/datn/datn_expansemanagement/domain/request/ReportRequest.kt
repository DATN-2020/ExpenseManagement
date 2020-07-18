package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class ReportRequest(
    @SerializedName("date")
    val date: String,
    @SerializedName("id_wallet")
    val idWallet: Int
)