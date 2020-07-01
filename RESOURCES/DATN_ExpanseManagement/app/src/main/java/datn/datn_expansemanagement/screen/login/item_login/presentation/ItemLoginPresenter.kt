package datn.datn_expansemanagement.screen.login.item_login.presentation

import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.PassportRequest
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemLoginPresenter(private val mvpActivity: MvpActivity) : ItemLoginContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun logInApp(passportRequest: PassportRequest) {
        view?.showLoading()
        val call = service?.login(passportRequest)
        call?.enqueue(object : Callback<PassportResponse> {
            override fun onFailure(call: Call<PassportResponse>, t: Throwable) {
                view?.handleLoginFail(t.message.getValueOrDefaultIsEmpty())
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<PassportResponse>,
                response: Response<PassportResponse>
            ) {
                ConfigUtil.savePassport(response.body())
                view?.handleAfterLogin(response.body()?.data?.checkWallet)
                view?.hideLoading()
            }
        })
    }
}