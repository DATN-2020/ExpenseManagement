package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName

data class WalletRequest(
    @SerializedName("User_Id") var userId: Int,
    @SerializedName("Amount") var amount: Double
)