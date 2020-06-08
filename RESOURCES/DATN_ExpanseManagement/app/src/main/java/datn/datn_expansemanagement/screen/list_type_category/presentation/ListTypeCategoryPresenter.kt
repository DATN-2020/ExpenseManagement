package datn.datn_expansemanagement.screen.list_type_category.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.TypeCategoryResponse
import datn.datn_expansemanagement.screen.list_type_category.domain.ListTypeCategoryMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListTypeCategoryPresenter(val mvpActivity: MvpActivity) :
    ListTypeCategoryContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)
    private val call = service?.getTypeCategory()
    override fun getData(typeId: Int?) {
        view?.showLoading()
        call?.enqueue(object : Callback<List<TypeCategoryResponse>> {
            override fun onFailure(call: Call<List<TypeCategoryResponse>>, t: Throwable) {
                Toast.makeText(mvpActivity,  t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<TypeCategoryResponse>>,
                response: Response<List<TypeCategoryResponse>>
            ) {
                view?.showData(ListTypeCategoryMapper(typeId).map(response.body()!!))
                view?.hideLoading()
            }
        })
    }
}