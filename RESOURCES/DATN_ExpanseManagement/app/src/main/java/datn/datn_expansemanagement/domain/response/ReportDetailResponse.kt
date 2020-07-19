package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


data class ReportDetailResponse(
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
        @SerializedName("date_come")
        val dateCome: String,
        @SerializedName("desciption")
        val desciption: String,
        @SerializedName("id_come")
        val idCome: Int,
        @SerializedName("idwallet")
        val idwallet: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("is_come")
        val isCome: Boolean,
        @SerializedName("name")
        val name: String
    )
}
