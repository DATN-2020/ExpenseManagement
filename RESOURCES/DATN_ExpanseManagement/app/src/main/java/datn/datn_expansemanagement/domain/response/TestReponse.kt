package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

data class TestReponse(
    @SerializedName("ad")
    val ad: Ad,
    @SerializedName("data")
    val list: List<User>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val per_page: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int
){
    data class Ad(
        @SerializedName("company")
        val company: String,
        @SerializedName("text")
        val text: String,
        @SerializedName("url")
        val url: String
    )

    data class User(
        @SerializedName("color")
        val color: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("pantone_value")
        val pantone_value: String,
        @SerializedName("year")
        val year: Int
    )
}
