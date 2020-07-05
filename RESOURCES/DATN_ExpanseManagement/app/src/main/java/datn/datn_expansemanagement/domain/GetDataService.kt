package datn.datn_expansemanagement.domain

import datn.datn_expansemanagement.domain.request.InOutComeRequest
import datn.datn_expansemanagement.domain.request.PassportRequest
import datn.datn_expansemanagement.domain.request.RegisterRequest
import datn.datn_expansemanagement.domain.request.WalletRequest
import datn.datn_expansemanagement.domain.response.*
import retrofit2.Call
import retrofit2.http.*


interface GetDataService {
    @GET("typecategories")
    fun getTypeCategory(): Call<List<TypeCategoryResponse>>

    @GET("wallets")
    fun getWallet(): Call<List<WalletResponse>>

    @GET("wallets")
    fun getItemWallet(@Query("Id_Wallet") walletId: Int): Call<GetItemWalletResponse>

    @GET("TypeWallets")
    fun getListTypeWallet(): Call<List<TypeWalletResponse>>

    @GET("exchange/export")
    fun getListExchangeRate(): Call<ExchangeRateResponse>

    @POST("logins")
    fun login(@Body passportRequest: PassportRequest): Call<PassportResponse>

    @POST("Wallets")
    fun createWallet(@Body walletRequest: WalletRequest): Call<BaseResponse>

    @POST("Users")
    fun createAccount(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("Income_Outcome")
    fun createInOutCome(@Body request: InOutComeRequest): Call<BaseResponse>

}