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
        @SerializedName("categoryId_Cate")
        val categoryIdCate: String,
        @SerializedName("date_come")
        val dateCome: String,
        @SerializedName("description_come")
        val descriptionCome: String,
        @SerializedName("id_Bill")
        val idBill: String,
        @SerializedName("id_Budget")
        val idBudget: String,
        @SerializedName("id_come")
        val idCome: Int,
        @SerializedName("id_Per")
        val idPer: String,
        @SerializedName("id_type")
        val idType: String,
        @SerializedName("is_Come")
        val isCome: Boolean,
        @SerializedName("loanId_Loan")
        val loanIdLoan: String,
        @SerializedName("tripId_Trip")
        val tripIdTrip: String,
        @SerializedName("walletId_Wallet")
        val walletIdWallet: String
    )
}