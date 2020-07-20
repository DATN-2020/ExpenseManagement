package datn.datn_expansemanagement.screen.control_saving.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.PutInWalletSavingRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.control_saving.domain.ControlSavingMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ControlSavingPresenter(private val mvpActivity: MvpActivity) : ControlSavingContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(isCome: Boolean?, data: ItemAccountAccumulationViewModel?) {
        view?.showData(ControlSavingMapper(isCome).map(""))
    }

    override fun saveControlSaving(request: PutInWalletSavingRequest) {
        view?.showLoading()
        val call = service?.putInWalletSaving(request)
        call?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                view?.handleAfterControl()
                view?.hideLoading()
            }

        })
    }

}