package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class TransferRequest(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("desciption")
    val desciption: String,
    @SerializedName("id_chuyen")
    val idChuyen: Int,
    @SerializedName("id_nhan")
    val idNhan: Int
)