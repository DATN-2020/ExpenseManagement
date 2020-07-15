package datn.datn_expansemanagement.screen.add_expanse.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.InOutComeRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.screen.add_expanse.domain.AddExpenseMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddExpensePresenter(private val screenNavigator: AndroidScreenNavigator, private val mvpActivity: MvpActivity) : AddExpenseContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData() {
        view?.showData(AddExpenseMapper().map(""))
    }

    override fun gotoHistoryActivity() {
        screenNavigator.gotoHistoryActivity()
    }

    override fun createExpense(inOutComeRequest: InOutComeRequest) {
        view?.showLoading()
        val call = service?.createInOutCome(inOutComeRequest)
        call?.enqueue(object : Callback<BaseResponse> {
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