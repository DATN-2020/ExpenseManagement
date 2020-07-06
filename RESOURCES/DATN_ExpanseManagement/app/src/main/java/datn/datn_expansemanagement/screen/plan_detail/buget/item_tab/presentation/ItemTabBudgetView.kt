package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity

class ItemTabBudgetView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
private val tabId: Int?): AndroidMvpView(mvpActivity, viewCreator), ItemTabBudgetContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layou_item_tab_control_detail_budget, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    override fun initCreateView() {

    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {

    }

}