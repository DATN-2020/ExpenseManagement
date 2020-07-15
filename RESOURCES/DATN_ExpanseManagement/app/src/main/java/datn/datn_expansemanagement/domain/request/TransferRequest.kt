package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class TransferRequest(
    @SerializedName("amount")
    var amount: Double,
    @SerializedName("date")
    var date: String,
    @SerializedName("disciption")
    var disciption: String,
    @SerializedName("id_chuyen")
    var idChuyen: Int,
    @SerializedName("id_nhan")
    var idNhan: Int
)