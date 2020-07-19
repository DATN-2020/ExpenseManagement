package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class TripRequest(
    @SerializedName("id")
    val id: String
)