package datn.datn_expansemanagement.domain

import datn.datn_expansemanagement.domain.request.PassportRequest
import datn.datn_expansemanagement.domain.response.ExchangeRateResponse
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.domain.response.TypeCategoryResponse
import datn.datn_expansemanagement.domain.response.WalletResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface GetDataService {
    @GET("typeCategories")
    fun getTypeCategory(): Call<List<TypeCategoryResponse>>

    @GET("wallets")
    fun getWallet(): Call<List<WalletResponse>>

    @GET("exchange/export")
    fun getListExchangeRate(): Call<ExchangeRateResponse>

    @POST("logins")
    fun login(@Body passportRequest: PassportRequest): Call<PassportResponse>
}