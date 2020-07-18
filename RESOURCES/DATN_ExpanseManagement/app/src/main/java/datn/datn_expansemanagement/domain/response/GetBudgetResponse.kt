package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class GetBudgetResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
){

    data class Data(
        @SerializedName("amount")
        val amount: Double,
        @SerializedName("check")
        val check: Boolean,
        @SerializedName("date_time_e")
        val dateTimeE: String,
        @SerializedName("date_time_s")
        val dateTimeS: String,
        @SerializedName("idBudget")
        val idBudget: Int,
        @SerializedName("idwallet")
        val idwallet: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("remain")
        val remain: Double,
        @SerializedName("time_e")
        val timeE: String,
        @SerializedName("time_remain")
        val timeRemain: String,
        @SerializedName("time_s")
        val timeS: String
    )
}