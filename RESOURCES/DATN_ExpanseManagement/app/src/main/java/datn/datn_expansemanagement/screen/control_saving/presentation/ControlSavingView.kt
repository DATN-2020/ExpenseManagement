package datn.datn_expansemanagement.screen.control_saving.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.renderer.AddWalletBottomItemViewRenderer
import datn.datn_expansemanagement.screen.control_saving.presentation.renderer.ControlSavingHeaderViewRenderer
import kotlinx.android.synthetic.main.layout_control_wallet.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
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
        mvpActivity.setFullScreen()
        if (isCome == true) {
            view.tvToolbar.text = "Gửi vào"
        } else {
            view.tvToolbar.text = "Rút ra"
        }
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgSave.gone()
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
        mPresenter.getData(isCome, data)
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

    private val onSave = object : OnActionNotify {
        override fun onActionNotify() {

        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvControlWallet, config)
        listViewMvp?.addViewRenderer(ControlSavingHeaderViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletBottomItemViewRenderer(mvpActivity, onSave))
        listViewMvp?.createView()
    }

}