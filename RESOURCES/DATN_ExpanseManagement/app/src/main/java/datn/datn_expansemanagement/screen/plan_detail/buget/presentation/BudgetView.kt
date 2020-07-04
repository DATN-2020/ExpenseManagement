package datn.datn_expansemanagement.screen.plan_detail.buget.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity

class BudgetView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), BudgetContract.View{
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_plan_detail, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    override fun initCreateView() {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showData(list: MutableList<ViewModel>) {
    }
}