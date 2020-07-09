package datn.datn_expansemanagement.screen.login.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.RegisterRequest
import datn.datn_expansemanagement.domain.response.RegisterResponse
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val screenNavigator: AndroidScreenNavigator) : LoginContract.Presenter() {
    override fun gotoMainActivity() {
        screenNavigator.gotoMainActivity()
    }

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun register(request: RegisterRequest) {
        view?.showLoading()
        val call = service?.createAccount(request)
        call?.enqueue(object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                view?.handleRegisterFail(t.message.toString())
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                view?.handleAfterRegister(response.body()!!)
                view?.hideLoading()
            }

        })
    }
}