package datn.datn_expansemanagement.domain.response

import com.google.gson.annotations.SerializedName

class TypeCategoryResponse(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("id_type")
    val idType: Int,
    @SerializedName("image_Type")
    val imageType: String,
    @SerializedName("name_Type")
    val nameType: String,
    @SerializedName("typeExpense")
    val typeExpense: String
) {
    data class Category(
        @SerializedName("id_Cate")
        val idCate: Int,
        @SerializedName("id_type")
        val idType: Int,
        @SerializedName("imageCate")
        val imageCate: String,
        @SerializedName("nameCate")
        val nameCate: String
    )
}