package datn.datn_expansemanagement.screen.add_plan.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.TimePeriodicResponse
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.add_plan.domain.AddPlanMapper
import datn.datn_expansemanagement.screen.add_plan.domain.AddTransactionMapper
import datn.datn_expansemanagement.screen.add_plan.domain.GetTimeMapper
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.domain.PlanDetailWalletMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPlanPresenter(
    private val screenNavigator: AndroidScreenNavigator,
    private val mvpActivity: MvpActivity
) : AddPlanContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(typeAdd: PlanItemViewModel?) {
        when (typeAdd?.type) {
            PlanItemViewModel.Type.BUDGET -> {
                view?.showData(AddPlanMapper().map(""))
            }
            PlanItemViewModel.Type.TRANSACTION -> {
                view?.showData(AddTransactionMapper().map(""))
            }
            else -> {
                view?.showData(AddTransactionMapper().map(""))
            }
        }
    }

    override fun getTime() {
        view?.showLoading()
        val call = service?.getTimePeriodic()
        call?.enqueue(object : Callback<List<TimePeriodicResponse>> {
            override fun onFailure(call: Call<List<TimePeriodicResponse>>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<List<TimePeriodicResponse>>,
                response: Response<List<TimePeriodicResponse>>
            ) {
                view?.showListTime(GetTimeMapper().map(response.body()!!))
                view?.hideLoading()
            }

        })

    }

    override fun gotoCategoryActivity(id: Int?) {
        screenNavigator.gotoCategoryActivity(id, true)
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