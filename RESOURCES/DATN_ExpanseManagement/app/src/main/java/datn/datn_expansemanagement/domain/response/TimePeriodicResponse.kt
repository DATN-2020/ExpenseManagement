package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


class TimePeriodicResponse(
    @SerializedName("desciption")
    val desciption: String,
    @SerializedName("id_Time")
    val idTime: Int
)