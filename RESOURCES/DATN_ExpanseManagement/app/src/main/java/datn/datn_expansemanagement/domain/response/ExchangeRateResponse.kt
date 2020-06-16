package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


data class ExchangeRateResponse(
    @SerializedName("items")
    val list: List<Item>
){

    data class Item(
        @SerializedName("banck")
        val banck: String,
        @SerializedName("bantienmat")
        val bantienmat: String,
        @SerializedName("imageurl")
        val imageurl: String,
        @SerializedName("muack")
        val muack: String,
        @SerializedName("muatienmat")
        val muatienmat: String,
        @SerializedName("type")
        val type: String
    )
}
