package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


data class BaseResponse(
    @SerializedName("data")
    val data: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
)