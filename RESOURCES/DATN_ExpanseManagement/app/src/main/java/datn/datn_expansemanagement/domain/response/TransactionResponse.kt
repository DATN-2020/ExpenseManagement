package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


data class TransactionResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
){
    data class Data(
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("date_e")
        val dateE: String,
        @SerializedName("date_s")
        val dateS: String,
        @SerializedName("idPeriodic")
        val idPeriodic: Int,
        @SerializedName("idwallet")
        val idwallet: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("is_Comeback")
        val isComeback: Boolean,
        @SerializedName("name")
        val name: String
    )
}