package datn.datn_expansemanagement.screen.account.item_account.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.renderer.ItemAccountAccumulationViewRenderer
import datn.datn_expansemanagement.screen.account.item_account.presentation.renderer.ItemAccountTotalMoneyViewRenderer
import datn.datn_expansemanagement.screen.account.item_account.presentation.renderer.WalletViewRenderer
import kotlinx.android.synthetic.main.layout_item_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ItemAccountView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
                      private val tabId : Int?) :
    AndroidMvpView(mvpActivity, viewCreator), ItemAccountContract.View{

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_item_account, context, viewGroup)

    private val mPresenter = ItemAccountPresenter()
    private val mResource = ItemAccountResource()
    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private lateinit var bottomDialogView : View

    private val onActionClick = object : OnActionData<WalletViewModel>{
        override fun onAction(data: WalletViewModel) {
            showBottomDialog(data)
        }
    }

    private val onActionClickMore = object : OnActionData<ItemAccountAccumulationViewModel>{
        override fun onAction(data: ItemAccountAccumulationViewModel) {
            showBottomDialogAccumulation(data)
        }
    }

    private fun showBottomDialog(data: WalletViewModel) {
        val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
        bottomDialogView = LayoutInflater.from(mvpActivity).inflate(R.layout.custom_bottom_sheet_account, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.show()
    }

    private fun showBottomDialogAccumulation(data: ItemAccountAccumulationViewModel) {
        val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
        bottomDialogView = LayoutInflater.from(mvpActivity).inflate(R.layout.custom_bottom_sheet_accumulation, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.show()
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
        mPresenter.getData(tabId.getValueOrDefaultIsZero())
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

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvItemAccount, renderConfig)
        listViewMvp?.addViewRenderer(WalletViewRenderer(mvpActivity, onActionClick))
        listViewMvp?.addViewRenderer(ItemAccountTotalMoneyViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(ItemAccountAccumulationViewRenderer(mvpActivity, onActionClickMore))
        listViewMvp?.createView()
    }
}