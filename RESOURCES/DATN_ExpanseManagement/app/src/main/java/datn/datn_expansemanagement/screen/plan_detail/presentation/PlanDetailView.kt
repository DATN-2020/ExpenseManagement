package datn.datn_expansemanagement.screen.plan_detail.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.BudgetFragment
import kotlinx.android.synthetic.main.layout_plan_detail.view.*

class PlanDetailView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val typePlan: PlanItemViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), PlanDetailContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_plan_detail, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = PlanDetailPresenter()

    override fun initCreateView() {
        replaceFragment(BudgetFragment())
        mvpActivity.setFullScreen()
    }

    private fun replaceFragment(frm: Fragment) {
        mvpActivity.supportFragmentManager.beginTransaction()
            .replace(view.flChange.id, frm)
            .commit()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
}