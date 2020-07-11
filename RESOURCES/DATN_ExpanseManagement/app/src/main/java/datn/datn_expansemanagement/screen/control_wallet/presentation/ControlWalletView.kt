package datn.datn_expansemanagement.screen.control_wallet.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.domain.request.UpdateWalletRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletDesViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletHeaderViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.renderer.ControlWalletDesViewRenderer
import datn.datn_expansemanagement.screen.control_wallet.presentation.renderer.ControlWalletHeaderViewRenderer
import datn.datn_expansemanagement.screen.control_wallet.presentation.renderer.ControlWalletTitleViewRenderer
import datn.datn_expansemanagement.screen.control_wallet.presentation.renderer.ControlWalletToItemViewRenderer
import kotlinx.android.synthetic.main.layout_control_wallet.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ControlWalletView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val data: WalletViewModel?,
    private val isOtherWallet: Boolean?
) : AndroidMvpView(mvpActivity, viewCreator), ControlWalletContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_control_wallet, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ControlWalletPresenter()
    private val listData = mutableListOf<ViewModel>()
    private val render = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val config = LinearRenderConfigFactory(render).create()
    private var listViewMvp: ListViewMvp? = null

    override fun initCreateView() {
        initRecycleView()
        mvpActivity.setFullScreen()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }

        view.imgSave.setOnClickListener {
            if (isOtherWallet == true) {

            } else {
                val user = ConfigUtil.passport
                if (user != null) {
                    var nameWallet : String? = null
                    var price : Double? = null
                    var des : String? = null
                    listData.forEach {
                        if(it is ControlWalletHeaderViewModel){
                            nameWallet = it.nameWallet
                            price = it.price
                        }
                        if(it is ControlWalletDesViewModel){
                            des = it.des
                        }
                    }
                    val request = UpdateWalletRequest(
                        userId = user.data.userId.getValueOrDefaultIsZero(),
                        nameWallet = nameWallet.getValueOrDefaultIsEmpty(),
                        amountWallet = price.getValueOrDefaultIsZero(),
                        description = des.getValueOrDefaultIsEmpty()
                    )
                    mPresenter.updateWallet(data?.id.getValueOrDefaultIsZero(), request)
                }
            }
        }
        if (isOtherWallet == true) {
            view.tvToolbar.text = "Chuyển tiền"
        } else {
            view.tvToolbar.text = "Chỉnh sửa số dư"
        }
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun initData() {
        super.initData()
        data?.let { mPresenter.getData(it, isOtherWallet) }
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvControlWallet, config)
        listViewMvp?.addViewRenderer(ControlWalletHeaderViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ControlWalletDesViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ControlWalletTitleViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ControlWalletToItemViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    override fun handleAfterUpdate() {

    }

}