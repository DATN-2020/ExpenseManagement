package datn.datn_expansemanagement.screen.control_saving.presentation

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
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.domain.request.PutInWalletSavingRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.account.item_account.presentation.ItemAccountResource
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.renderer.AddWalletBottomItemViewRenderer
import datn.datn_expansemanagement.screen.control_saving.presentation.model.ControlSavingHeaderViewModel
import datn.datn_expansemanagement.screen.control_saving.presentation.renderer.ControlSavingHeaderViewRenderer
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.layout_control_wallet.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*

class ControlSavingView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val isCome: Boolean? = false,
    private val data: ItemAccountAccumulationViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), ControlSavingContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_control_wallet, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ControlSavingPresenter(mvpActivity)
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
        mvpActivity.onBackPressed()
    }

    private val onSave = object : OnActionNotify {
        override fun onActionNotify() {
            var price = 0.0
            listData.forEach {
                if(it is ControlSavingHeaderViewModel){
                    if(it.price != 0.0){
                        price = it.price
                    }else{
                        showDialogNotify("Bạn chưa nhập giá trị cần thực hiện thao tác này")
                        return
                    }
                }
            }
            var request : PutInWalletSavingRequest? = null
            request = if(isCome == true){
                PutInWalletSavingRequest(
                    idSaving = data?.id.getValueOrDefaultIsZero(),
                    dateTrans = getCurrentDate(),
                    nameTrans = "Gửi vào",
                    priceTrans = price,
                    isIncome = true
                )
            }else{
                PutInWalletSavingRequest(
                    idSaving = data?.id.getValueOrDefaultIsZero(),
                    dateTrans = getCurrentDate(),
                    nameTrans = "Rút ra",
                    priceTrans = price,
                    isIncome = false
                )
            }
            mPresenter.saveControlSaving(request)
        }

    }

    private fun showDialogNotify(title: String? = null) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
        }
        if(!title.isNullOrEmpty()){
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    private val mResource = ItemAccountResource()
    private fun setDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun getCurrentDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }
    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvControlWallet, config)
        listViewMvp?.addViewRenderer(ControlSavingHeaderViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletBottomItemViewRenderer(mvpActivity, onSave))
        listViewMvp?.createView()
    }

}