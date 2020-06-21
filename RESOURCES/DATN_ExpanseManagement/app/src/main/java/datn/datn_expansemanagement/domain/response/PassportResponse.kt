package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class PassportResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String
)