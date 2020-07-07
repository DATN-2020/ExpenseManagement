package datn.datn_expansemanagement.domain.request

import com.google.gson.annotations.SerializedName

data class GetWalletRequest(
    @SerializedName("id")
    val id: Int
)