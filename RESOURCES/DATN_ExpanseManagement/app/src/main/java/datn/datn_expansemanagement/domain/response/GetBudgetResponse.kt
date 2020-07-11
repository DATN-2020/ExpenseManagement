package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class GetBudgetResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {
    data class Data(
        @SerializedName("amount")
        val amount: Int,
        @SerializedName("idwallet")
        val idwallet: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("isFinnish")
        val isFinnish: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("remain")
        val remain: Int,
        @SerializedName("time_e")
        val timeE: String,
        @SerializedName("time_remain")
        val timeRemain: String,
        @SerializedName("time_s")
        val timeS: String
    )
}

