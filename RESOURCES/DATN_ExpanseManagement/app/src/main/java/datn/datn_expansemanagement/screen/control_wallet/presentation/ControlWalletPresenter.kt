package datn.datn_expansemanagement.screen.control_wallet.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.GetItemWalletResponse
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.domain.ControlWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControlWalletPresenter(private val mvpActivity: MvpActivity) : ControlWalletContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(data: WalletViewModel) {
        view?.showLoading()
        val call = service?.getItemWallet(data.id)
        call?.enqueue(object : Callback<GetItemWalletResponse>{
            override fun onFailure(call: Call<GetItemWalletResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<GetItemWalletResponse>,
                response: Response<GetItemWalletResponse>
            ) {
                view?.showData(ControlWalletMapper().map(response.body()!!))
                view?.hideLoading()
            }

        })
    }

}