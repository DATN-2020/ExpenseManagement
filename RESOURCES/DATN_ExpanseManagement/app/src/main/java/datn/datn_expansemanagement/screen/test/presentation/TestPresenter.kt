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

    }

}