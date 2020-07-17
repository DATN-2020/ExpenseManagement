package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.BillItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.BudgetItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.NoDataViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.TransactionItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.presentation.PlanDetailResource
import kotlinx.android.synthetic.main.layou_item_tab_control_detail_budget.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ItemTabBudgetView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
private val tabId: TabItemViewModel?, private val idWallet: Int): AndroidMvpView(mvpActivity, viewCreator), ItemTabBudgetContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layou_item_tab_control_detail_budget, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemTabBudgetPresenter(mvpActivity)
    private val mResource = PlanDetailResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null

    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        initRecycleView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        tabId?.let { mPresenter.getData(it, idWallet) }
    }

    override fun startMvpView() {
        tabId?.let { mPresenter.getData(it, idWallet) }
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvControlDetailBudget, renderConfig)
        listViewMvp?.addViewRenderer(BudgetItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(TransactionItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(NoDataViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(BillItemViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }

}