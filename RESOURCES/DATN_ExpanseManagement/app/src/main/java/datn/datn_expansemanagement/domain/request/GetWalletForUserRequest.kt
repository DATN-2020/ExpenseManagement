package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName

data class GetWalletForUserRequest(
    @SerializedName("User_Id")
    val userId: Int
)