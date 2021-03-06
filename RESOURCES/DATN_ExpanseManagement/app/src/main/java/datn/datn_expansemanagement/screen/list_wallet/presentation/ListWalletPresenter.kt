package datn.datn_expansemanagement.screen.list_wallet.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.GetWalletForUserRequest
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.screen.list_wallet.domain.ListWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListWalletPresenter(private val mvpActivity: MvpActivity) : ListWalletContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(walletId: Int?) {
        view?.showLoading()
        val data = ConfigUtil.passport
        if(data != null){
            val call = service?.getWalletForUser(data.data.userId)
            call?.enqueue(object : Callback<WalletResponse> {
                override fun onFailure(call: Call<WalletResponse>, t: Throwable) {
                    Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                    view?.hideLoading()
                }

                override fun onResponse(
                    call: Call<WalletResponse>,
                    response: Response<WalletResponse>
                ) {
                    view?.showData(ListWalletMapper(walletId).map(response.body()!!))
                    view?.hideLoading()
                }
            })
        }

    }

    override fun gotoCreateWalletActivity() {

    }

}