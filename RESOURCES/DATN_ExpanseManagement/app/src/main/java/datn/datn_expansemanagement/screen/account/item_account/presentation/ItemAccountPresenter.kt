package datn.datn_expansemanagement.screen.account.item_account.presentation

import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.GetWalletForUserRequest
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.screen.account.item_account.domain.ItemAccountMapper
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemAccountPresenter(private val screenNavigator: AndroidScreenNavigator) :
    ItemAccountContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(tabId: Int, userId: Int) {
        view?.showLoading()
        val call = service?.getWalletForUser(userId)
        call?.enqueue(object : Callback<WalletResponse>{
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<WalletResponse>,
                response: Response<WalletResponse>
            ) {
                view?.showData(ItemAccountMapper(tabId).map(response.body()!!))
                view?.hideLoading()
            }

        })

    }

    override fun gotoControlWallet(data: WalletViewModel) {
        screenNavigator.gotoControlWalletActivity(data)
    }

}