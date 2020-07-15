package datn.datn_expansemanagement.screen.account.item_account.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.account.item_account.data.OnReportWalletDataBus
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.renderer.ItemAccountAccumulationViewRenderer
import datn.datn_expansemanagement.screen.account.item_account.presentation.renderer.ItemAccountTotalMoneyViewRenderer
import datn.datn_expansemanagement.screen.account.item_account.presentation.renderer.WalletViewRenderer
import kotlinx.android.synthetic.main.custom_bottom_sheet_account.*
import kotlinx.android.synthetic.main.custom_bottom_sheet_accumulation.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.btnCancel
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.tvTitleChooseDate
import kotlinx.android.synthetic.main.custom_dialog_notify.*
import kotlinx.android.synthetic.main.layout_item_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ItemAccountView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val tabId: Int?
) :
    AndroidMvpView(mvpActivity, viewCreator), ItemAccountContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_item_account, context, viewGroup)

    private val mPresenter =
        ItemAccountPresenter(AndroidScreenNavigator(mvpActivity), mvpActivity = mvpActivity)
    private val mResource = ItemAccountResource()
    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private lateinit var bottomDialogView: View
    private val user = ConfigUtil.passport
    private var isBack: Boolean = false

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

    private val onActionClick = object : OnActionData<WalletViewModel> {
        override fun onAction(data: WalletViewModel) {
            showBottomDialog(data)
        }
    }

    private val onItemClick = object : OnActionData<WalletViewModel> {
        override fun onAction(data: WalletViewModel) {
            eventBusLifeCycle.sendData(OnReportWalletDataBus(data.id.getValueOrDefaultIsZero()))
        }
    }

    private val onItemClickAccumulation = object : OnActionData<ItemAccountAccumulationViewModel> {
        override fun onAction(data: ItemAccountAccumulationViewModel) {
            eventBusLifeCycle.sendData(
                OnReportWalletDataBus(
                    data.id.getValueOrDefaultIsZero(),
                    isCard = true
                )
            )
        }
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        mPresenter.getData(
            tabId.getValueOrDefaultIsZero(),
            user?.data?.userId.getValueOrDefaultIsZero()
        )
    }

    private val onActionClickMore = object : OnActionData<ItemAccountAccumulationViewModel> {
        override fun onAction(data: ItemAccountAccumulationViewModel) {
            showBottomDialogAccumulation(data)
        }
    }

    private val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
    private fun showBottomDialog(data: WalletViewModel) {

        bottomDialogView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_bottom_sheet_account, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.create()
        setDialogFullScreen(bottomDialog)
        bottomDialog.show()

        bottomDialog.llControl.setOnClickListener {
            isBack = true
            mPresenter.gotoControlWallet(data, false)
        }

        bottomDialog.llDelete.setOnClickListener {
            showDialogNotify(title = "Bạn có chắc chắn muốn xoá ví này ??", data = data)
        }

        bottomDialog.llUpdate.setOnClickListener {
            isBack = true
            mPresenter.gotoControlWallet(data, true)
        }
    }

    private fun setAlertDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private val layoutView = LayoutInflater.from(mvpActivity)
        .inflate(R.layout.custom_dialog_notify, null, false)
    private val dialogRegister =
        AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()

    private fun showDialogNotify(title: String? = null, data: WalletViewModel) {

        setAlertDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
        }

        dialogRegister.btnOk.text = "Đồng ý"
        dialogRegister.btnOk.setOnClickListener {
            mPresenter.deleteWallet(data.id.getValueOrDefaultIsZero())
        }

        if (!title.isNullOrEmpty()) {
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    private fun showBottomDialogAccumulation(data: ItemAccountAccumulationViewModel) {
        val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
        bottomDialogView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_bottom_sheet_accumulation, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.create()
        setDialogFullScreen(bottomDialog)
        bottomDialog.show()

        bottomDialog.clSendTo.setOnClickListener {

        }

        bottomDialog.clDelete.setOnClickListener {

        }

        bottomDialog.clUpdate.setOnClickListener {

        }

        bottomDialog.clPutOut.setOnClickListener {

        }

        bottomDialog.clFinish.setOnClickListener {

        }
    }

    private fun setDialogFullScreen(dialog: BottomSheetDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        initRecycleView()
        view.refresh.setOnRefreshListener(onRefreshListener)
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
        view.refresh.isRefreshing = false
    }

    override fun initData() {
        super.initData()
        mPresenter.getData(
            tabId.getValueOrDefaultIsZero(),
            user?.data?.userId.getValueOrDefaultIsZero()
        )
    }

    override fun startMvpView() {
        if (isBack) {
            bottomDialog.dismiss()
            mPresenter.getData(
                tabId.getValueOrDefaultIsZero(),
                user?.data?.userId.getValueOrDefaultIsZero()
            )
        }
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

    override fun handleAfterDeleteWallet() {
        dialogRegister.dismiss()
        bottomDialog.dismiss()
        mPresenter.getData(
            tabId.getValueOrDefaultIsZero(),
            user?.data?.userId.getValueOrDefaultIsZero()
        )
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvItemAccount, renderConfig)
        listViewMvp?.addViewRenderer(WalletViewRenderer(mvpActivity, onActionClick, onItemClick))
        listViewMvp?.addViewRenderer(ItemAccountTotalMoneyViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(
            ItemAccountAccumulationViewRenderer(
                mvpActivity,
                onActionClickMore,
                onItemClickAccumulation
            )
        )
        listViewMvp?.createView()
    }
}