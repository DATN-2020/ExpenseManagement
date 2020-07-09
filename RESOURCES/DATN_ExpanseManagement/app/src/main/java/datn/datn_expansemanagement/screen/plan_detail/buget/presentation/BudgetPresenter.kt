package datn.datn_expansemanagement.screen.plan_detail.buget.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel
import datn.datn_expansemanagement.screen.report.domain.GetWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetPresenter(private val screenNavigator: AndroidScreenNavigator) : BudgetContract.Presenter(){

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(typePlan: PlanItemViewModel) {
        val list = mutableListOf<ViewModel>()
        list.add(TabItemViewModel(
            id = 0,
            name = "Đang áp dụng",
            typePlanId = typePlan.type
        ))

        list.add(TabItemViewModel(
            id = 1,
            name = "Đã kết thúc",
            typePlanId = typePlan.type
        ))
        view?.showData(list)
    }

    override fun gotoAddPlanActivity(typeAdd: TypeAddViewModel) {
        screenNavigator.gotoAddPlanActivity(typeAdd)
    }

    override fun getWalletForUser(idWallet: Int?) {
        view?.showLoading()
        val userId = ConfigUtil.passport?.data?.userId.getValueOrDefaultIsZero()
        val call = service?.getWalletForUser(userId)
        call?.enqueue(object : Callback<WalletResponse> {
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<WalletResponse>,
                response: Response<WalletResponse>
            ) {
                view?.showData(GetWalletMapper(idWallet).map(response.body()!!))
                view?.hideLoading()
            }

        })
    }
}