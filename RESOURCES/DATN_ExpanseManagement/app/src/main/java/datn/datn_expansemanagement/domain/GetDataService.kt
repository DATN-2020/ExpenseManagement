package datn.datn_expansemanagement.domain

import datn.datn_expansemanagement.domain.request.PassportRequest
import datn.datn_expansemanagement.domain.request.RegisterRequest
import datn.datn_expansemanagement.domain.request.WalletRequest
import datn.datn_expansemanagement.domain.response.*
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

    @POST("CreateWallets")
    fun createWallet(@Body walletRequest: WalletRequest): Call<BaseResponse>

    @POST("Users")
    fun createAccount(@Body request: RegisterRequest): Call<RegisterResponse>

}