package datn.datn_expansemanagement.screen.trip.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.request.AddTripRequest
import datn.datn_expansemanagement.domain.request.TripRequest
import datn.datn_expansemanagement.domain.request.UpdateTripRequest
import datn.datn_expansemanagement.domain.response.BaseResponse
import datn.datn_expansemanagement.domain.response.TripResponse
import datn.datn_expansemanagement.screen.trip.domain.TripMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripPresenter(private val mvpActivity: MvpActivity) : TripContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)


    override fun getData(request: TripRequest) {
        view?.showLoading()
        val data = ConfigUtil.passport
        if (data != null) {
            val call = service?.getTrip(data.data.userId.toString())
            call?.enqueue(object : Callback<TripResponse> {
                override fun onFailure(call: Call<TripResponse>, t: Throwable) {
                    Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                    view?.hideLoading()
                }

                override fun onResponse(
                    call: Call<TripResponse>,
                    response: Response<TripResponse>
                ) {
                    view?.showData(TripMapper().map(response.body()!!))
                    view?.hideLoading()
                }
            })
        }
    }

    override fun addTrip(request: AddTripRequest) {
        view?.showLoading()
        val call = service?.addTrip(request)
        call?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                view?.reload("add")
                view?.hideLoading()
            }
        })

    }

    override fun deleteTrip(idTrip: Int) {
        view?.showLoading()
        val call = service?.deleteTrip(idTrip)
        call?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                view?.reload("delete")
                view?.hideLoading()
            }
        })
    }

    override fun updateTrip(idTrip: Int, request: UpdateTripRequest) {
        view?.showLoading()
        val call = service?.updateTrip(idTrip, request)
        call?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(mvpActivity, t.message, Toast.LENGTH_LONG).show()
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                view?.reload("update")
                view?.hideLoading()
            }
        })
    }
}