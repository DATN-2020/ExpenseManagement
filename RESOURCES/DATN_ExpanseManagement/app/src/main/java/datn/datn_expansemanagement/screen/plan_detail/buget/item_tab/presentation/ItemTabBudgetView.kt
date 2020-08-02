package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.domain.request.InOutComeRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.account.item_account.presentation.ItemAccountResource
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.add_expanse.presentation.AddExpenseResource
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BillItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.TransactionItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.BillItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.BudgetItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.NoDataViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.TransactionItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.presentation.PlanDetailResource
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import kotlinx.android.synthetic.main.custom_bottom_sheet_account.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.btnCancel
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.tvTitleChooseDate
import kotlinx.android.synthetic.main.custom_dialog_notify.*
import kotlinx.android.synthetic.main.layou_item_tab_control_detail_budget.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*

class ItemTabBudgetView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val tabId: TabItemViewModel?, private val idWallet: Int
) : AndroidMvpView(mvpActivity, viewCreator), ItemTabBudgetContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(
            R.layout.layou_item_tab_control_detail_budget,
            context,
            viewGroup
        )

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemTabBudgetPresenter(
        mvpActivity = mvpActivity,
        screenNavigator = AndroidScreenNavigator(mvpActivity)
    )
    private val mResource = PlanDetailResource()
    private val listData = mutableListOf<ViewModel>()
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

    override fun initData() {
        super.initData()
//        tabId?.let { mPresenter.getData(it, idWallet) }
    }

    override fun startMvpView() {
        tabId?.let { mPresenter.getData(it, idWallet) }
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

    private fun getCurrentDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }

    private val onActionPayBill = object : OnActionData<BillItemViewModel> {
        override fun onAction(data: BillItemViewModel) {
            showDialogNotifyBill("Xác nhận trả hóa đơn này", data)
        }

    }

    private fun setDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResourceProvider.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_notify, null, false)
    private val dialogNotify =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()

    private fun showDialogNotifyBill(
            title: String? = null,
            data: BillItemViewModel? = null
    ) {

        setDialogFullScreen(dialogNotify)
        dialogNotify.show()
        dialogNotify.btnCancel.setOnClickListener {
            dialogNotify.dismiss()
        }

        dialogNotify.btnOk.text = "Đồng ý"
        dialogNotify.btnOk.setOnClickListener {
            if(data != null){
                var request: InOutComeRequest? = null
                listData.forEach {
                    if (it is BillItemViewModel) {
                        request = InOutComeRequest(
                                walletIdWallet = it.idWallet.toString(),
                                idBill = it.idBill.toString(),
                                dateCome = getCurrentDate()
                        )
                    }
                }
                request?.let { mPresenter.payBill(it) }
                dialogNotify.dismiss()
            }
        }

        if (!title.isNullOrEmpty()) {
            dialogNotify.tvTitleChooseDate.text = title
        }
    }
    private val mResourceProvider = AddExpenseResource()
    private fun showDialogNotify(title: String? = null) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            tabId?.let { it1 -> mPresenter.getData(it1, idWallet) }
            dialogRegister.dismiss()
        }
        if (!title.isNullOrEmpty()) {
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    override fun handleAfterPayBill() {
        showDialogNotify("Thanh toán hóa đơn thành công")
    }

    private val onLongBudgetClick = object : OnActionData<BudgetItemViewModel>{
        override fun onAction(data: BudgetItemViewModel) {
            showBottomDialog(data)
        }

    }

    private fun setDialogFullScreen(dialog: BottomSheetDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            dialog.window?.statusBarColor = mResourceProvider.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private val bottomDialog = BottomSheetDialog(mvpActivity, R.style.BaseBottomSheetDialog)
    private fun showBottomDialog(data: ViewModel) {

        val bottomDialogView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_bottom_sheet_account, null, false)
        bottomDialog.setContentView(bottomDialogView)
        bottomDialog.create()
        setDialogFullScreen(bottomDialog)
        bottomDialog.show()
        bottomDialog.llUpdate.gone()

        bottomDialog.llDelete.setOnClickListener {
            showDialogDelete(title = "Bạn có chắc chắn muốn xoá ví này ??", data = data)
        }
    }

    private fun showDialogDelete(
        title: String? = null,
        data: ViewModel? = null
    ) {

        setDialogFullScreen(dialogNotify)
        dialogNotify.show()
        dialogNotify.btnCancel.setOnClickListener {
            dialogNotify.dismiss()
        }

        dialogNotify.btnOk.text = "Đồng ý"
        dialogNotify.btnOk.setOnClickListener {
            if (data != null) {
                if(data is BudgetItemViewModel){
                    mPresenter.deleteBudget(data.id.getValueOrDefaultIsZero())

                }
            }
        }

        if (!title.isNullOrEmpty()) {
            dialogNotify.tvTitleChooseDate.text = title
        }
    }

    private val onActionItemRvClickedListener = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            var request: ReportViewModel? = null
            when (dataItem) {
                is BudgetItemViewModel -> {
                    request = ReportViewModel(
                        idWallet = dataItem.idWallet,
                        idBudget = dataItem.id
                    )
                }
                is TransactionItemViewModel -> {
                    request = ReportViewModel(
                        idWallet = dataItem.idWallet,
                        idPeriodic = dataItem.id
                    )
                }
                is BillItemViewModel -> {
                    request = ReportViewModel(
                        idWallet = dataItem.idWallet,
                        idBill = dataItem.idBill
                    )
                }
            }

            if (request != null) {
                mPresenter.gotoReportDetail(request)
            }

        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvControlDetailBudget, renderConfig)
        listViewMvp?.addViewRenderer(BudgetItemViewRenderer(mvpActivity, mResource, onLongBudgetClick))
        listViewMvp?.addViewRenderer(TransactionItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(NoDataViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(BillItemViewRenderer(mvpActivity, onActionPayBill))
        listViewMvp?.setOnItemRvClickedListener(onActionItemRvClickedListener)
        listViewMvp?.createView()
    }

}