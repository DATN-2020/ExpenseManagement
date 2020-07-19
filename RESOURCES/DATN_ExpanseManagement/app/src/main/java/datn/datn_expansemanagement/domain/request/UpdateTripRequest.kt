package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class UpdateTripRequest(
    @SerializedName("Name_Trip")
    val nameTrip: String
)