package datn.datn_expansemanagement.domain.request
import com.google.gson.annotations.SerializedName


data class AddTripRequest(
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("Name_Trip")
    val nameTrip: String
)