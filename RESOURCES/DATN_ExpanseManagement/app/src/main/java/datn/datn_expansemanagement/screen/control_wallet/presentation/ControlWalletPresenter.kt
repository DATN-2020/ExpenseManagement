package datn.datn_expansemanagement.screen.control_wallet.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.domain.ControlWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControlWalletPresenter :
    ControlWalletContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(data: WalletViewModel, isOtherWallet: Boolean?) {
        view?.showData(ControlWalletMapper(isOtherWallet).map(data))
    }

    override fun updateWallet(idWallet: Int) {
        view?.showLoading()
        val call = service?.updateWallet(idWallet)
        call?.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                view?.hideLoading()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleAfterUpdate()
                view?.hideLoading()
            }

        })
    }

}