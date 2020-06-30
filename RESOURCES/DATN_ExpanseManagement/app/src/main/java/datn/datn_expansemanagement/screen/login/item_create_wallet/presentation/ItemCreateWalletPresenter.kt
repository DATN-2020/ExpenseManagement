package datn.datn_expansemanagement.screen.login.item_create_wallet.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.WalletRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemCreateWalletPresenter : ItemCreateWalletContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun createWallet(walletRequest: WalletRequest) {
        val call = service?.createWallet(walletRequest)
        view?.showLoading()
        call?.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                view?.handleCreateWalletFail(t.message.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleCreateWallet()
            }

        })
    }

}