package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName


data class ReportResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: String
) {

    data class Data(
        @SerializedName("beginBalance")
        val beginBalance: Double,
        @SerializedName("date")
        val date: String,
        @SerializedName("endBalance")
        val endBalance: Double,
        @SerializedName("id_Summary")
        val idSummary: Int,
        @SerializedName("id_wallet")
        val idWallet: String,
        @SerializedName("netBalance")
        val netBalance: Double,
        @SerializedName("totalBorrow")
        val totalBorrow: Double,
        @SerializedName("totalIncome")
        val totalIncome: Double,
        @SerializedName("totalIncome_Outcome_1")
        val totalIncomeOutcome1: Double,
        @SerializedName("totalIncome_Outcome_10")
        val totalIncomeOutcome10: Double,
        @SerializedName("totalIncome_Outcome_11")
        val totalIncomeOutcome11: Double,
        @SerializedName("totalIncome_Outcome_12")
        val totalIncomeOutcome12: Double,
        @SerializedName("totalIncome_Outcome_2")
        val totalIncomeOutcome2: Double,
        @SerializedName("totalIncome_Outcome_3")
        val totalIncomeOutcome3: Double,
        @SerializedName("totalIncome_Outcome_4")
        val totalIncomeOutcome4: Double,
        @SerializedName("totalIncome_Outcome_5")
        val totalIncomeOutcome5: Double,
        @SerializedName("totalIncome_Outcome_6")
        val totalIncomeOutcome6: Double,
        @SerializedName("totalIncome_Outcome_7")
        val totalIncomeOutcome7: Double,
        @SerializedName("totalIncome_Outcome_8")
        val totalIncomeOutcome8: Double,
        @SerializedName("totalIncome_Outcome_9")
        val totalIncomeOutcome9: Double,
        @SerializedName("totalLoan")
        val totalLoan: Double,
        @SerializedName("totalOther")
        val totalOther: Double,
        @SerializedName("totalOutcome")
        val totalOutcome: Double
    )
}