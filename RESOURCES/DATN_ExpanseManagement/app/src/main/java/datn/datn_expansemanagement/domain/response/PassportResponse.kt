package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class PassportResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {
    data class Data(
        @SerializedName("fullName")
        val fullName: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("user_Id")
        val userId: Int,
        @SerializedName("user_Name")
        val userName: String
    )
}

