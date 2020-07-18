package datn.datn_expansemanagement.screen.report.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.ReportRequest
import datn.datn_expansemanagement.domain.response.ReportResponse
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.domain.GetWalletMapper
import datn.datn_expansemanagement.screen.report.domain.ReportCreditCardMapper
import datn.datn_expansemanagement.screen.report.domain.ReportDefaultMapper
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportPresenter(
    private val screenNavigator: AndroidScreenNavigator,
    private val mvpActivity: MvpActivity
) : ReportContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(idWallet: Int?, isCreditCard: Boolean, date: String?) {
        view?.showLoading()
        val call = service?.getReport(date = date.getValueOrDefaultIsEmpty(), idWallet = idWallet.getValueOrDefaultIsZero())
        if (isCreditCard) {
            view?.showData(ReportCreditCardMapper().map(""))
        } else {
            call?.enqueue(object : Callback<ReportResponse> {
                override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                    Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                    view?.hideLoading()
                }

                override fun onResponse(
                    call: Call<ReportResponse>,
                    response: Response<ReportResponse>
                ) {
                    view?.showData(ReportDefaultMapper().map(response.body()!!))
                    view?.hideLoading()
                }

            })

        }

    }

    override fun gotoReportDetailActivity(data: ReportViewModel) {
        screenNavigator.gotoReportDetailActivity(data)
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
                view?.handleAfterGetWallet(GetWalletMapper(idWallet).map(response.body()!!))
                view?.hideLoading()
            }

        })
    }

}