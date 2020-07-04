package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.presentation

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.domain.request.WalletRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.*
import datn.datn_expansemanagement.screen.add_wallet.presentation.renderer.*
import kotlinx.android.synthetic.main.custom_bottom_sheet_type_wallet.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.item_layout_wallet.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class DefaultWalletView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
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

    private val onActionNotify= object : OnActionNotify{
        override fun onActionNotify() {
            var amount = 0.0
            var nameWallet: String? = null
            var des : String? = null
            var idType: Int? = null

            listData.forEach {
                if(it is AddWalletHeaderItemViewModel){
                    amount = it.price.getValueOrDefaultIsZero()
                }
                if(it is AddWalletNameItemViewModel){
                    nameWallet = it.name.getValueOrDefaultIsEmpty()
                }
                if(it is AddWalletNoteItemViewModel){
                    des = it.note.getValueOrDefaultIsEmpty()
                }
                if(it is AddWalletTypeItemViewModel){
                    idType = it.id.getValueOrDefaultIsZero()
                }
            }

            val user = ConfigUtil.passport
            if(user != null){
                val request = WalletRequest(
                    userId = user.data.userId.getValueOrDefaultIsZero(),
                    nameWallet = nameWallet,
                    description = des,
                    idTypeWallet = idType.getValueOrDefaultIsZero(),
                    amountWallet = amount.getValueOrDefaultIsZero()
                )
                mPresenter.createWallet(request)
            }
        }
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

    private val onActionData = object : OnActionData<AddWalletTypeItemViewModel> {
        override fun onAction(data: AddWalletTypeItemViewModel) {
            mPresenter.getListTypeWallet()
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

    private val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
    private val onChooseTypeWallet = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as TypeWalletItemViewModel
            listData.forEach {
                if (it is AddWalletTypeItemViewModel) {
                    it.id = dataItem.id
                    it.name = dataItem.name
                }
            }

            listViewMvp?.notifyDataChanged()
            bottomDialog.dismiss()
        }

    }

    override fun showListTypeWallet(list: MutableList<ViewModel>) {
        val bottomDialogView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_bottom_sheet_type_wallet, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.create()
        setDialogFullScreen(bottomDialog)
        bottomDialog.show()
        val input = LinearRenderConfigFactory.Input(
            context = mvpActivity,
            orientation = LinearRenderConfigFactory.Orientation.VERTICAL
        )
        val config = LinearRenderConfigFactory(input).create()
        val listMvp = ListViewMvp(mvpActivity, bottomDialog.rvTypeWallet, config)
        listMvp.addViewRenderer(TypeWalletItemViewRenderer(mvpActivity))
        listMvp.setOnItemRvClickedListener(onChooseTypeWallet)
        listMvp.createView()
        listMvp.setItems(list)
        listMvp.notifyDataChanged()
    }

    override fun handleCreateWallet() {
        mvpActivity.onBackPressed()
    }

    override fun handleCreateWalletFail(message: String) {
        showBottomDialog(message)
    }

    private fun showBottomDialog(ms: String) {
        val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
        val bottomDialogView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.create()
        setDialogFullScreen(bottomDialog)
        bottomDialog.show()
        bottomDialog.tvTitle.text = ms
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvItemAddWallet, renderConfig)
        listViewMvp?.addViewRenderer(AddWalletHeaderItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(AddWalletNameItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(
            AddWalletTypeItemViewRenderer(
                mvpActivity,
                mResource,
                onActionData
            )
        )
        listViewMvp?.addViewRenderer(AddWalletNoteItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletBottomItemViewRenderer(mvpActivity, onActionNotify))
        listViewMvp?.createView()
    }
}