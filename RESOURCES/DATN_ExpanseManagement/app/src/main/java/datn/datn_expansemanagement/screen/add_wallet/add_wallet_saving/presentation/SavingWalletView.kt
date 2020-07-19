package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import android.content.Context
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletRateItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.renderer.*
import datn.datn_expansemanagement.screen.main_plan.presentation.renderer.EmptyLineViewRenderer
import kotlinx.android.synthetic.main.item_layout_wallet.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class SavingWalletView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), SavingWalletContact.View{

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_wallet, context, viewGroup)
    private val mPresenter = SavingWalletPresenter()
    private val listData = mutableListOf<ViewModel>()
    private val mResource = AddWalletResource()
    private var listViewMvp: ListViewMvp? = null
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

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData()
    }

    private val onActionData = object : OnActionData<AddWalletTypeItemViewModel> {
        override fun onAction(data: AddWalletTypeItemViewModel) {
        }
    }

    private val onActionUpdateTotalPrice = object : OnActionData<AddWalletHeaderItemViewModel> {
        override fun onAction(data: AddWalletHeaderItemViewModel) {
            listData.forEach {
                if(it is AddWalletRateItemViewModel && it.isResult){
                    it.price = data.price
                    listViewMvp?.notifyItemChanged(listData.indexOf(it))
                }
            }
        }
    }


    private val onActionSave = object : OnActionNotify{
        override fun onActionNotify() {

        }
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if(list.isNotEmpty()){
            this.listData.addAll(list)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvItemAddWallet, renderConfig)
        listViewMvp?.addViewRenderer(AddWalletHeaderItemViewRenderer(mvpActivity, mResource, onActionUpdateTotalPrice))
        listViewMvp?.addViewRenderer(AddWalletNameItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(AddWalletBottomItemViewRenderer(mvpActivity, onActionSave))
        listViewMvp?.addViewRenderer(EmptyLineViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletRateItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletTypeItemViewRenderer(mvpActivity, mResource, onActionData))
        listViewMvp?.createView()
    }
}