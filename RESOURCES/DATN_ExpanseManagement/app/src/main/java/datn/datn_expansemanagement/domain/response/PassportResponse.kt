package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class PassportResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status_code")
    val statusCode: String? = null
){
    data class Data(
        @SerializedName("check_Wallet")
        val checkWallet: Boolean,
        @SerializedName("fullName")
        val fullName: String,
        @SerializedName("password")
        val password: String? = null,
        @SerializedName("userCategories")
        val userCategories: Any? = null,
        @SerializedName("user_Id")
        val userId: Int,
        @SerializedName("user_Name")
        val userName: String,
        @SerializedName("wallets")
        val wallets: Any? = null
    )
}

