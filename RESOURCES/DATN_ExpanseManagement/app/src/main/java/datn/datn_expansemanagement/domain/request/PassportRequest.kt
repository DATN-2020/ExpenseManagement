package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName

data class PassportRequest(
    @SerializedName("User_Name") var user: String,
    @SerializedName("Password") var pass: String
)