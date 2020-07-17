package datn.datn_expansemanagement.screen.main_plan.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.renderer.EmptyLineViewRenderer
import datn.datn_expansemanagement.screen.main_plan.presentation.renderer.PlanDesItemViewRenderer
import datn.datn_expansemanagement.screen.main_plan.presentation.renderer.PlanItemViewRenderer
import kotlinx.android.synthetic.main.layout_overview.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class OverviewView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), OverviewContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
    AndroidMvpView.LayoutViewCreator(R.layout.layout_overview, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = OverviewPresenter(AndroidScreenNavigator(mvpActivity))
    private val mResource = OverviewResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp : ListViewMvp? = null

    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    override fun initCreateView() {
        initRecycleView()
        view.imgAdd.gone()
    }

    private val onAction = object :OnActionData<PlanItemViewModel>{
        override fun onAction(data: PlanItemViewModel) {
            mPresenter.gotoPlanDetailActivity(data)
        }
    }

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvOverview, renderConfig)
        listViewMvp?.addViewRenderer(PlanItemViewRenderer(mvpActivity, onAction))
        listViewMvp?.addViewRenderer(EmptyLineViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(PlanDesItemViewRenderer(mvpActivity))
        listViewMvp?.createView()
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
        if(list.isNotEmpty()){
            this.listData.addAll(list)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }
}