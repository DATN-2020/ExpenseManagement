package datn.datn_expansemanagement.screen.list_wallet.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.screen.list_wallet.domain.ListWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListWalletPresenter : ListWalletContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(walletId: Int?) {
        view?.showLoading()
        val call = service?.getWallet()
        call?.enqueue(object : Callback<List<WalletResponse>> {
            override fun onFailure(call: Call<List<WalletResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<WalletResponse>>,
                response: Response<List<WalletResponse>>
            ) {
                view?.showData(ListWalletMapper(walletId).map(response.body()!!))
                view?.hideLoading()
            }
        })
    }

    override fun gotoCreateWalletActivity() {

    }

}