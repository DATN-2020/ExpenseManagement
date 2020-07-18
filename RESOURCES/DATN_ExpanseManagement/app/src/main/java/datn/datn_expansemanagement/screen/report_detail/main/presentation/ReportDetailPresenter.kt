package datn.datn_expansemanagement.screen.report_detail.main.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.ReportDetailResponse
import datn.datn_expansemanagement.domain.response.ReportResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.domain.ReportDefaultMapper
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report_detail.domain.ReportDetailMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportDetailPresenter(private val mvpActivity: MvpActivity) :
    ReportDetailContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)


    override fun getData(data: ReportViewModel?) {
        view?.showLoading()
        val call = service?.getReportDetail(
            date = data?.date.getValueOrDefaultIsEmpty(),
            idWallet = data?.idWallet.getValueOrDefaultIsZero()
        )
        call?.enqueue(object : Callback<ReportDetailResponse> {
            override fun onFailure(call: Call<ReportDetailResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<ReportDetailResponse>,
                response: Response<ReportDetailResponse>
            ) {
                view?.showData(ReportDetailMapper().map(response.body()!!))
                view?.hideLoading()
            }

        })
//        view?.showData(ReportDetailMapper().map(""))
    }

}