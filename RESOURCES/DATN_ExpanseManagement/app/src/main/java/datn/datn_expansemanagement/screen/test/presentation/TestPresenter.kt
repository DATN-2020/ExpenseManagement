package datn.datn_expansemanagement.screen.test.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.TestReponse
import datn.datn_expansemanagement.screen.test.domain.TestMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class TestPresenter : TestContract.Presenter(){
    override fun getData() {
        view?.showLoading()
        val apiInterface =  RetrofitClientInstance().getClient()?.create<GetDataService>()
        val call = apiInterface?.getListUser()
        call?.enqueue(object : Callback<TestReponse>{
            override fun onFailure(call: Call<TestReponse>, t: Throwable) {
                view?.hideLoading()
            }

            override fun onResponse(call: Call<TestReponse>, response: Response<TestReponse>) {
                view?.hideLoading()
                view?.showData(TestMapper().map(response.body()!!))
            }
        })
    }

}