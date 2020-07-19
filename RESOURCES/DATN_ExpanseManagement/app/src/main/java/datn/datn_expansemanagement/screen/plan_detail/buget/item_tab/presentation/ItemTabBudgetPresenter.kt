package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.InOutComeRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.domain.response.BillResponse
import datn.datn_expansemanagement.domain.response.GetBudgetResponse
import datn.datn_expansemanagement.domain.response.TransactionResponse
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain.ItemBillMapper
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain.ItemTabBudgetMapper
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain.ItemTransactionMapper
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemTabBudgetPresenter(
    private val mvpActivity: MvpActivity,
    private val screenNavigator: AndroidScreenNavigator
) : ItemTabBudgetContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(tab: TabItemViewModel, idWallet: Int) {
        view?.showLoading()
        when (tab.typePlanId) {
            PlanItemViewModel.Type.BUDGET -> {
                val call = service?.getBudgets(idWallet)
                call?.enqueue(object : Callback<GetBudgetResponse> {
                    override fun onFailure(call: Call<GetBudgetResponse>, t: Throwable) {
                        Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                        view?.hideLoading()
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
            PlanItemViewModel.Type.TRANSACTION -> {
                val call = service?.getTransactions(idWallet)
                call?.enqueue(object : Callback<TransactionResponse> {
                    override fun onFailure(call: Call<TransactionResponse>, t: Throwable) {
                        Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                        view?.hideLoading()
                    }

                    override fun onResponse(
                        call: Call<TransactionResponse>,
                        response: Response<TransactionResponse>
                    ) {
                        view?.showData(ItemTransactionMapper(tab).map(response.body()!!))
                        view?.hideLoading()
                    }

                })
            }
            else -> {
                val call = service?.getBills(idWallet)
                call?.enqueue(object : Callback<BillResponse> {
                    override fun onFailure(call: Call<BillResponse>, t: Throwable) {
                        Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                        view?.hideLoading()
                    }

                    override fun onResponse(
                        call: Call<BillResponse>,
                        response: Response<BillResponse>
                    ) {
                        view?.showData(ItemBillMapper(tab).map(response.body()!!))
                        view?.hideLoading()
                    }

                })
            }
        }
    }

    override fun payBill(request: InOutComeRequest) {
        val call = service?.createInOutCome(request)
        call?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                view?.handleAfterPayBill()
                view?.hideLoading()
            }

        })
    }

    override fun gotoReportDetail(data: ReportViewModel) {
        screenNavigator.gotoReportDetailActivity(data)
    }
}