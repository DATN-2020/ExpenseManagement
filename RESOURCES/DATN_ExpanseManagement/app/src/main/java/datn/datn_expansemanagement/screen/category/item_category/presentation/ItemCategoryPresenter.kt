package datn.datn_expansemanagement.screen.category.item_category.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.TypeCategoryResponse
import datn.datn_expansemanagement.screen.category.item_category.domain.ItemCategoryMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemCategoryPresenter : ItemCategoryContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(tabName: String, categoryId: Int?) {
        view?.showLoading()
        val call = service?.getTypeCategory()
        call?.enqueue(object : Callback<List<TypeCategoryResponse>> {
            override fun onFailure(call: Call<List<TypeCategoryResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<TypeCategoryResponse>>,
                response: Response<List<TypeCategoryResponse>>
            ) {
                view?.showData(ItemCategoryMapper(tabName, categoryId).map(response.body()!!))
                view?.hideLoading()
            }

        })

    }
}