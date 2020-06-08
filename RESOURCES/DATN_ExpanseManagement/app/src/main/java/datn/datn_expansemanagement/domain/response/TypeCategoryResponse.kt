package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


class TypeCategoryResponse(
    @SerializedName("idType")
    val idType: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
