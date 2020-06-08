package datn.datn_expansemanagement.domain

import datn.datn_expansemanagement.domain.response.TypeCategoryResponse
import retrofit2.Call
import retrofit2.http.GET


interface GetDataService {
    @GET("TypeCategories")
    fun getTypeCategory(): Call<List<TypeCategoryResponse>>
}