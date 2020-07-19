package datn.datn_expansemanagement.screen.control_saving.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ControlSavingView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val isCome: Boolean? = false,
    private val data: ItemAccountAccumulationViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), ControlSavingContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_control_wallet, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ControlSavingPresenter()
    private val listData = mutableListOf<ViewModel>()
    private val render = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val config = LinearRenderConfigFactory(render).create()
    private var listViewMvp: ListViewMvp? = null

    override fun initCreateView() {
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

    override fun handleAfterControl() {
    }

}