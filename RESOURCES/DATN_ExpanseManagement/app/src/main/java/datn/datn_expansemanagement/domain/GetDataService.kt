package datn.datn_expansemanagement.domain

import datn.datn_expansemanagement.domain.request.*
import datn.datn_expansemanagement.domain.response.*
import retrofit2.Call
import retrofit2.http.*


interface GetDataService {
    @GET("typecategories")
    fun getTypeCategory(): Call<List<TypeCategoryResponse>>

    @GET("GetWallets/5")
    fun getWalletForUser(@Query("id") userId: Int): Call<WalletResponse>

    @GET("wallets")
    fun getItemWallet(@Query("Id_Wallet") walletId: Int): Call<GetItemWalletResponse>

    @PUT("wallets/{id}")
    fun updateWallet(
        @Path("id") walletId: Int,
        @Body request: UpdateWalletRequest
    ): Call<BaseResponse>

    @DELETE("wallets/5")
    fun deleteWallet(@Query("id") walletId: Int): Call<BaseResponse>

    @GET("getBudgets/5")
    fun getBudgets(@Query("id") walletId: Int): Call<GetBudgetResponse>

    @GET("getPeriodics/5")
    fun getTransactions(@Query("id") walletId: Int): Call<TransactionResponse>

    @GET("getBills/5")
    fun getBills(@Query("id") walletId: Int): Call<BillResponse>

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