package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

class BankResponse(
    @SerializedName("id_Bank")
    val idBank: Int,
    @SerializedName("interest")
    val interest: Double,
    @SerializedName("name_Bank")
    val nameBank: String
)