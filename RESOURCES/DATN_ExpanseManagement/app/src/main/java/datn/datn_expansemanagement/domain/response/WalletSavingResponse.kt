package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


data class WalletSavingResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {

    data class Data(
        @SerializedName("date_e")
        val dateE: String,
        @SerializedName("date_s")
        val dateS: String,
        @SerializedName("id_saving")
        val idSaving: Int,
        @SerializedName("id_user")
        val idUser: String,
        @SerializedName("interest")
        val interest: Double,
        @SerializedName("is_Finish")
        val isFinish: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("name_bank")
        val nameBank: String,
        @SerializedName("price")
        val price: Double,
        @SerializedName("price_end")
        val priceEnd: Double
    )
}