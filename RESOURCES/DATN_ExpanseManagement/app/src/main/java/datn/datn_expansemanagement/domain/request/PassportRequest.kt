package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName

data class PassportRequest(
    @SerializedName("username") var user: String,
    @SerializedName("password") var pass: String
)