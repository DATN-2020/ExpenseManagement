package datn.datn_expansemanagement.screen.information.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.information.presentation.model.InfoHeaderItemViewModel
import datn.datn_expansemanagement.screen.information.presentation.renderer.InfoBottomViewRenderer
import datn.datn_expansemanagement.screen.information.presentation.renderer.InfoHeaderItemViewRenderer
import datn.datn_expansemanagement.screen.information.presentation.renderer.InfoItemViewRenderer
import kotlinx.android.synthetic.main.layout_information.view.*
import kotlinx.android.synthetic.main.toolbar_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class InformationView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), InformationContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_information, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = InformationPresenter(AndroidScreenNavigator(mvpActivity))
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        view.tvToolbar.text = "Thông tin cá nhân"
        view.imgAdd.gone()
        initRecycleView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showToast(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private val onChooseAvatar = object : OnActionData<InfoHeaderItemViewModel> {
        override fun onAction(data: InfoHeaderItemViewModel) {

        }

    }

    private val onActionLogout = object : OnActionNotify {
        override fun onActionNotify() {
            ConfigUtil.savePassport(null)
            mPresenter.gotoLoginActivity()
        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvInformation, renderConfig)
        listViewMvp?.addViewRenderer(InfoHeaderItemViewRenderer(mvpActivity, onChooseAvatar))
        listViewMvp?.addViewRenderer(InfoItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(InfoBottomViewRenderer(mvpActivity, onActionLogout))
        listViewMvp?.createView()
    }

    override fun initData() {
        super.initData()
        ConfigUtil.passport?.let { mPresenter.getData(it) }
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
}