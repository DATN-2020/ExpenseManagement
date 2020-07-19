package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class TripResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {

    data class Data(
        @SerializedName("id_Trip")
        val idTrip: Int,
        @SerializedName("id_user")
        val idUser: String,
        @SerializedName("name_Trip")
        val nameTrip: String
    )
}
