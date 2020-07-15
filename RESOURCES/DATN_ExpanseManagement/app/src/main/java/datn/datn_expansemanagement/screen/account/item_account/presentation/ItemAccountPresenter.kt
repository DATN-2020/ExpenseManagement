package datn.datn_expansemanagement.screen.account.item_account.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.GetWalletForUserRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.screen.account.item_account.domain.ItemAccountMapper
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemAccountPresenter(private val screenNavigator: AndroidScreenNavigator, private val mvpActivity: MvpActivity) :
    ItemAccountContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(tabId: Int, userId: Int) {
        view?.showLoading()
        val call = service?.getWalletForUser(userId)
        call?.enqueue(object : Callback<WalletResponse>{
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
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

    override fun gotoControlWallet(data: WalletViewModel, isOtherWallet: Boolean) {
        screenNavigator.gotoControlWalletActivity(data, isOtherWallet)
    }

    override fun deleteWallet(walletId: Int) {
        view?.showLoading()
        val call = service?.deleteWallet(walletId)
        call?.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleAfterDeleteWallet()
                view?.hideLoading()
            }

        })
    }
}