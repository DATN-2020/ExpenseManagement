package datn.datn_expansemanagement.screen.add_expense_receive.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer.AddExpenseReceiveCategoryRenderer
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer.AddExpenseReceiveInfoRenderer
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer.AddExpenseReceiveTotalMoneyRenderer
import kotlinx.android.synthetic.main.layout_add_expense_receive.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddExpenseReceiveView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseReceiveContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expense_receive, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val listData = mutableListOf<ViewModel>()
    private val mResource = AddExpenseReceiveResource()
    private val mPresenter = AddExpenseReceivePresenter()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val onClickExpand = object : OnActionData<AddExpenseReceiveInfoViewModel> {
        override fun onAction(data: AddExpenseReceiveInfoViewModel) {
            data.isExpand = !data.isExpand
            listViewMvp?.notifyDataChanged()
        }
    }

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
        mPresenter.getData()
    }

    override fun startMvpView() {
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

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddExpanse, renderConfig)
        listViewMvp?.addViewRenderer(AddExpenseReceiveCategoryRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddExpenseReceiveInfoRenderer(mvpActivity, mResource, onClickExpand))
        listViewMvp?.addViewRenderer(AddExpenseReceiveTotalMoneyRenderer(mvpActivity))
        listViewMvp?.createView()
    }
}