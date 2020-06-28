package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.renderer.*
import kotlinx.android.synthetic.main.item_layout_wallet.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class DefaultWalletView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), DefaultWalletContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_wallet, context, viewGroup)

    private val listData = mutableListOf<ViewModel>()
    private val mPresenter = DefaultWalletPresenter()
    private var listViewMvp: ListViewMvp? = null
    private val mResource = AddWalletResource()
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

    private fun showDialog(){

    }

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvItemAddWallet, renderConfig)
        listViewMvp?.addViewRenderer(AddWalletHeaderItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(AddWalletNameItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(AddWalletTypeItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(AddWalletNoteItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletBottomItemViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }
}