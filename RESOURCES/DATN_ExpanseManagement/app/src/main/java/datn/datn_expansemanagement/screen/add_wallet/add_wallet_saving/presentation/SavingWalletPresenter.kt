package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.WalletSavingRequest
import datn.datn_expansemanagement.domain.response.BankResponse
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain.GetListBankMapper
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain.SavingWalletMapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SavingWalletPresenter(private val mvpActivity: MvpActivity) : SavingWalletContact.Presenter() {
    private val mResource = AddWalletResource()
    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData() {
        view?.showData(SavingWalletMapper(mResource).map(""))
    }

    override fun getListBank() {
        view?.showLoading()
        val call = service?.getListBank()
        call?.enqueue(object : Callback<List<BankResponse>> {
            override fun onFailure(call: Call<List<BankResponse>>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<List<BankResponse>>,
                response: Response<List<BankResponse>>
            ) {
                view?.showListBank(GetListBankMapper().map(response.body()!!))
                view?.hideLoading()
            }

        })
    }

    override fun createWalletSaving(request: WalletSavingRequest) {
        view?.showLoading()
        val call = service?.createWalletSaving(request)
        call?.enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleAfterCreate()
                view?.hideLoading()
            }

        })
    }
}