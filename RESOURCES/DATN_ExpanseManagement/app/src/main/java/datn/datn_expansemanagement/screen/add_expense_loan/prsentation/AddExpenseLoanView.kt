package datn.datn_expansemanagement.screen.add_expense_loan.prsentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.renderer.AddExpenseLoanTotalMoneyRenderer
import kotlinx.android.synthetic.main.layout_add_expense_donate.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddExpenseLoanView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
private val isDonate : Boolean = false): AndroidMvpView(mvpActivity, viewCreator), AddExpenseLoanContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expense_donate, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val mPresenter = AddExpenseLoanPresenter()
    private val mResourceProvider = AddExpenseDonateResource()

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
        mPresenter.getData(isDonate)
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

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddExpanse, renderConfig)
        listViewMvp?.addViewRenderer(AddExpenseLoanTotalMoneyRenderer(mvpActivity, mResourceProvider))
        listViewMvp?.createView()
    }

}