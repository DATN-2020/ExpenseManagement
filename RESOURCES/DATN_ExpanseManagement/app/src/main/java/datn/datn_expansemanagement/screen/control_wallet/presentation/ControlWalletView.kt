package datn.datn_expansemanagement.screen.control_wallet.presentation

import android.app.AlertDialog
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
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.domain.request.TransferRequest
import datn.datn_expansemanagement.domain.request.UpdateWalletRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletDesViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletHeaderViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletListBottomViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletToViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.renderer.*
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.layout_control_wallet.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*

class ControlWalletView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val data: WalletViewModel?,
    private val isOtherWallet: Boolean?
) : AndroidMvpView(mvpActivity, viewCreator), ControlWalletContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_control_wallet, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ControlWalletPresenter(mvpActivity)
    private val listData = mutableListOf<ViewModel>()
    private val render = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val config = LinearRenderConfigFactory(render).create()
    private var listViewMvp: ListViewMvp? = null

    private val listBottom = mutableListOf<ViewModel>()
    private var listViewBottom: ListViewMvp? = null

    private val renderBottom = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfigBottom = LinearRenderConfigFactory(renderBottom).create()
    private val customView = LayoutInflater.from(mvpActivity)
        .inflate(R.layout.custom_bottomsheet_recycleview, null, false)
    private val bottomSheet = BottomSheetDialog(mvpActivity)

    private fun getCurrentDate(): String {
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }

    override fun initCreateView() {
        initRecycleView()
        mvpActivity.setFullScreen()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }

        view.imgSave.setOnClickListener {
            val user = ConfigUtil.passport
            if (isOtherWallet == true) {
                val fromId = data?.id.getValueOrDefaultIsZero()
                var toId: Int? = null
                var price: Double? = null
                var des: String? = null

                listData.forEach {
                    if(it is ControlWalletToViewModel){
                        if(it.id != null){
                            toId = it.id
                        }
                    }
                    if(it is ControlWalletHeaderViewModel){
                        price = it.price
                    }
                    if(it is ControlWalletDesViewModel){
                        des = it.des
                    }
                }

                val request = TransferRequest(
                    idChuyen = fromId,
                    idNhan = toId.getValueOrDefaultIsZero(),
                    desciption = des.getValueOrDefaultIsEmpty(),
                    amount = price.getValueOrDefaultIsZero()
                )
                mPresenter.transferWallet(request)
            } else {
                if (user != null) {
                    var nameWallet: String? = null
                    var price: Double? = null
                    var des: String? = null
                    listData.forEach {
                        if (it is ControlWalletHeaderViewModel) {
                            nameWallet = it.nameWallet
                            price = it.price
                        }
                        if (it is ControlWalletDesViewModel) {
                            des = it.des
                        }
                    }
                    val request = UpdateWalletRequest(
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

    private val mResource = AddWalletResource()
    private fun setAlertDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun showDialogNotify(title: String? = null) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setAlertDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
            mvpActivity.onBackPressed()
        }
        if(!title.isNullOrEmpty()){
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    override fun handleTransferWallet() {
        showDialogNotify(title = "Chuyển tiền thành công")
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

    private val onActionData = object : OnActionData<ControlWalletToViewModel> {
        override fun onAction(item: ControlWalletToViewModel) {
            val user = ConfigUtil.passport
            if (user != null) {
                mPresenter.getListWallet(user.data.userId, data?.id.getValueOrDefaultIsZero())
            }

        }

    }

    override fun showListWallet(list: MutableList<ViewModel>) {
        listBottom.clear()
        if (list.isNotEmpty()) {
            listBottom.addAll(list)
        }
        listViewBottom?.setItems(this.listBottom)
        listViewBottom?.notifyDataChanged()

        bottomSheet.show()
    }

    private val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as ControlWalletListBottomViewModel
            listData.forEach {
                if (it is ControlWalletToViewModel) {
                    it.id = dataItem.id.getValueOrDefaultIsZero()
                    it.name = dataItem.name.getValueOrDefaultIsEmpty()
                    listViewMvp?.notifyItemChanged(listData.indexOf(it))
                }
            }
            bottomSheet.dismiss()
        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvControlWallet, config)
        listViewMvp?.addViewRenderer(ControlWalletHeaderViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ControlWalletDesViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ControlWalletTitleViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ControlWalletToItemViewRenderer(mvpActivity, onActionData))
        listViewMvp?.createView()

        bottomSheet.setContentView(customView)
        bottomSheet.create()
        listViewBottom = ListViewMvp(mvpActivity, bottomSheet.rvChoose, renderConfigBottom)
        listViewBottom?.addViewRenderer(ControlWalletListBottomViewRenderer(mvpActivity))
        listViewBottom?.setOnItemRvClickedListener(onItemRvClickedListener)
        listViewBottom?.createView()
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
        mvpActivity.onBackPressed()
    }

}