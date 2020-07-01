package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class PassportResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
){
    data class Data(
        @SerializedName("check_Wallet")
        val checkWallet: Boolean,
        @SerializedName("fullName")
        val fullName: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("userCategories")
        val userCategories: Any,
        @SerializedName("user_Id")
        val userId: Int,
        @SerializedName("user_Name")
        val userName: String,
        @SerializedName("wallets")
        val wallets: Any
    )
}

