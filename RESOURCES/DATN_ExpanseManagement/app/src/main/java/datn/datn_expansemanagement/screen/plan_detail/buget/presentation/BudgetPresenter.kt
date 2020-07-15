package datn.datn_expansemanagement.screen.plan_detail.buget.presentation

import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.domain.PlanDetailWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BudgetPresenter(private val screenNavigator: AndroidScreenNavigator, private val mvpActivity: MvpActivity) : BudgetContract.Presenter(){

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

    override fun gotoAddPlanActivity(typeAdd: PlanItemViewModel) {
        screenNavigator.gotoAddPlanActivity(typeAdd)
    }

    override fun getWalletForUser(idWallet: Int?) {
        view?.showLoading()
        val userId = ConfigUtil.passport?.data?.userId.getValueOrDefaultIsZero()
        val call = service?.getWalletForUser(userId)
        call?.enqueue(object : Callback<WalletResponse> {
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<WalletResponse>,
                response: Response<WalletResponse>
            ) {
                view?.handleAfterGetWallet(PlanDetailWalletMapper(idWallet).map(response.body()!!))
                view?.hideLoading()
            }

        })
    }
}