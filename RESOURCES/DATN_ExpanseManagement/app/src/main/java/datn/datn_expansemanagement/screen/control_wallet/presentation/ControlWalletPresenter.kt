package datn.datn_expansemanagement.screen.control_wallet.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.TransferRequest
import datn.datn_expansemanagement.domain.request.UpdateWalletRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.domain.ControlListWalletMapper
import datn.datn_expansemanagement.screen.control_wallet.domain.ControlWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControlWalletPresenter(private val mvpActivity: MvpActivity) :
    ControlWalletContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(data: WalletViewModel, isOtherWallet: Boolean?) {
        view?.showData(ControlWalletMapper(isOtherWallet).map(data))
    }

    override fun updateWallet(idWallet: Int, request: UpdateWalletRequest) {
        view?.showLoading()
        val call = service?.updateWallet(idWallet, request)
        call?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleAfterUpdate()
                view?.hideLoading()
            }

        })
    }

    override fun transferWallet(transferRequest: TransferRequest) {
        view?.showLoading()
        val call = service?.transferWallet(transferRequest)
        call?.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleTransferWallet()
                view?.hideLoading()
            }

        })
    }

    override fun getListWallet(userId: Int, idWallet: Int) {
        view?.showLoading()
        val call = service?.getWalletForUser(userId)
        call?.enqueue(object : Callback<WalletResponse>{
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<WalletResponse>,
                response: Response<WalletResponse>
            ) {
                view?.showListWallet(ControlListWalletMapper(idWallet).map(response.body()!!))
                view?.hideLoading()
            }

        })
    }
}