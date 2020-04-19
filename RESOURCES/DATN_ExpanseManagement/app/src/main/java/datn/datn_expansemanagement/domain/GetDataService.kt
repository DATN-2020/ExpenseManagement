package datn.datn_expansemanagement.domain

import datn.datn_expansemanagement.domain.response.TestReponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface GetDataService {
    @GET("/api/user")
    fun getListUser(): Call<TestReponse>
}