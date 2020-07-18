package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


data class BillResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {

    data class Data(
        @SerializedName("amount")
        val amount: Double,
        @SerializedName("date_e")
        val dateE: String,
        @SerializedName("date_s")
        val dateS: String,
        @SerializedName("date_time_e")
        val dateTimeE: String,
        @SerializedName("date_time_s")
        val dateTimeS: String,
        @SerializedName("idBill")
        val idBill: Int,
        @SerializedName("idwallet")
        val idwallet: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("isFinnish")
        val isFinnish: Boolean,
        @SerializedName("isPay")
        val isPay: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("time")
        val time: String
    )
}