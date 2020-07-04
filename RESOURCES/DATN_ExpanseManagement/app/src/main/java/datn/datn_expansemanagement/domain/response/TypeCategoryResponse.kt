package datn.datn_expansemanagement.domain.response
import com.google.gson.annotations.SerializedName


class TypeCategoryResponse(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_Type")
    val imageType: String,
    @SerializedName("income_Outcomes")
    val incomeOutcomes: Any? = null,
    @SerializedName("name_Type")
    val nameType: String,
    @SerializedName("typeExpense")
    val typeExpense: String
) {
    data class Category(
        @SerializedName("id_Cate")
        val idCate: Int,
        @SerializedName("imageCate")
        val imageCate: String,
        @SerializedName("income_Outcomes")
        val incomeOutcomes: Any? = null,
        @SerializedName("nameCate")
        val nameCate: String,
        @SerializedName("userCategories")
        val userCategories: Any? = null
    )
}
