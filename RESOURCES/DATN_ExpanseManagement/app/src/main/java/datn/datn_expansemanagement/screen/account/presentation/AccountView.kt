package datn.datn_expansemanagement.screen.account.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.account.presentation.renderer.WalletViewRenderer
import kotlinx.android.synthetic.main.layout_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AccountView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator),AccountContract.View{
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_account, context, viewGroup)

    private val mPresenter = AccountPresenter()
    private val listAccount = mutableListOf<ViewModel>()
    private var listData : ListViewMvp? = null

    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
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
        this.listAccount.clear()
        if(list.isNotEmpty()){
            this.listAccount.addAll(list)
        }
        listData?.setItems(this.listAccount)
        listData?.notifyDataChanged()
    }
    private fun initRecycleView(){
        listData = ListViewMvp(mvpActivity, view.rvAccount, renderConfig)
        listData?.addViewRenderer(WalletViewRenderer(mvpActivity))
        listData?.createView()
    }

}