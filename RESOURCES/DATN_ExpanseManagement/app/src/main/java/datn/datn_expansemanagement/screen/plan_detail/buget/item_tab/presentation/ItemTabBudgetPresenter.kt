package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.GetBudgetResponse
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain.ItemTabBudgetMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemTabBudgetPresenter : ItemTabBudgetContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(tab: TabItemViewModel, idWallet: Int) {
        view?.showLoading()
        val call = service?.getBudgets(idWallet)
        call?.enqueue(object : Callback<GetBudgetResponse>{
            override fun onFailure(call: Call<GetBudgetResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<GetBudgetResponse>,
                response: Response<GetBudgetResponse>
            ) {
                view?.showData(ItemTabBudgetMapper(tab).map(response.body()!!))
                view?.hideLoading()
            }

        })

    }

}