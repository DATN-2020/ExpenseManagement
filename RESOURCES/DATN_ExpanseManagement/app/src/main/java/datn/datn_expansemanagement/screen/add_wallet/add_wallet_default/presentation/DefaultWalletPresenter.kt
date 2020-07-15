package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.WalletRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.domain.response.TypeWalletResponse
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.domain.DefaultWalletMapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.mapper.TypeWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultWalletPresenter(private val mvpActivity: MvpActivity) : DefaultWalletContract.Presenter() {
    private val mResource = AddWalletResource()
    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData() {
        view?.showData(DefaultWalletMapper(mResource).map(""))
    }

    override fun getListTypeWallet() {
        view?.showLoading()
        val call = service?.getListTypeWallet()
        call?.enqueue(object : Callback<List<TypeWalletResponse>> {
            override fun onFailure(call: Call<List<TypeWalletResponse>>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<List<TypeWalletResponse>>,
                response: Response<List<TypeWalletResponse>>
            ) {
                view?.showListTypeWallet(TypeWalletMapper().map(response.body()!!))
                view?.hideLoading()
            }

        })
    }

    override fun createWallet(request: WalletRequest) {
        val call = service?.createWallet(request)
        view?.showLoading()
        call?.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                view?.handleCreateWalletFail(t.message.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleCreateWallet()
                view?.hideLoading()
            }

        })
    }
}