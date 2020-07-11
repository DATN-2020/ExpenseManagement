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
        @SerializedName("idBill")
        val idBill: Int,
        @SerializedName("idwallet")
        val idwallet: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("isDeadline")
        val isDeadline: Boolean,
        @SerializedName("isPay")
        val isPay: Boolean,
        @SerializedName("name")
        val name: String
    )
}