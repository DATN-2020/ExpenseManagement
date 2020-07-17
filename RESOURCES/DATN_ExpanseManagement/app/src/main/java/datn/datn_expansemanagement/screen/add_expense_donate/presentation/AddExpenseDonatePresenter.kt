package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import android.widget.Toast
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.GetBudgetResponse
import datn.datn_expansemanagement.screen.add_expense_donate.domain.AddExpenseDonateMapper
import datn.datn_expansemanagement.screen.add_expense_donate.domain.GetBudgetMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddExpenseDonatePresenter(
    private val screenNavigator: AndroidScreenNavigator,
    private val mvpActivity: MvpActivity
) : AddExpenseDonateContract.Presenter() {

    private val service = RetrofitClientInstance().getClient()?.create(GetDataService::class.java)

    override fun getData(isDonate: Boolean) {
        view?.showData(AddExpenseDonateMapper(isDonate).map(""))
    }

    override fun getListBudget(idWallet: Int) {
        view?.showLoading()
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
                view?.showBottomData(GetBudgetMapper().map(response.body()!!))
                view?.hideLoading()
            }

        })
    }

    override fun gotoCategoryActivity(categoryId: Int?) {
        screenNavigator.gotoCategoryActivity(categoryId)
    }

    override fun gotoChooseWalletActivity(walletId: Int?) {
        screenNavigator.gotoChooseWalletActivity(walletId)
    }

    override fun gotoChooseTripActivity() {
        screenNavigator.gotoChooseTripActivity()
    }

    override fun gotoChooseFriend() {
        screenNavigator.gotoChooseFriendActivity()
    }

    override fun gotoLocationActivity() {
        screenNavigator.gotoLocationActivity()
    }
}