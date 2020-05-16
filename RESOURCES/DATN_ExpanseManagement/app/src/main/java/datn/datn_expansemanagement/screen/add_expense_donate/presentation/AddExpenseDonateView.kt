package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateCategoryRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateInfoRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateTotalMoneyRenderer
import kotlinx.android.synthetic.main.item_layout_add_expanse_total_money.view.*
import kotlinx.android.synthetic.main.layout_add_expense_donate.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddExpenseDonateView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseDonateContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expense_donate, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mResource = AddExpenseDonateResource()
    private val mPresenter = AddExpenseDonatePresenter()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val onClickExpand = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
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
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddExpanseDonate, renderConfig)
        listViewMvp?.addViewRenderer(AddExpenseDonateCategoryRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(
            AddExpenseDonateInfoRenderer(
                mvpActivity,
                mResource,
                onClickExpand
            )
        )
        listViewMvp?.addViewRenderer(AddExpenseDonateTotalMoneyRenderer(mvpActivity, mResource))
        listViewMvp?.createView()
    }

}