package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


data class ReportWalletSavingResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {

    data class Data(
        @SerializedName("date_trans")
        val dateTrans: String,
        @SerializedName("id_saving")
        val idSaving: String,
        @SerializedName("id_trans")
        val idTrans: Int,
        @SerializedName("is_End")
        val isEnd: Boolean,
        @SerializedName("is_Income")
        val isIncome: Boolean,
        @SerializedName("name_trans")
        val nameTrans: String,
        @SerializedName("price_trans")
        val priceTrans: Double
    )
}
