package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.common.AppConstants
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.domain.request.WalletSavingRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation.model.BankItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation.renderer.BankItemViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNameItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletRateItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.renderer.*
import datn.datn_expansemanagement.screen.main_plan.presentation.renderer.EmptyLineViewRenderer
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.item_choose_date.*
import kotlinx.android.synthetic.main.item_choose_date.tvTitleChooseDate
import kotlinx.android.synthetic.main.item_layout_wallet.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat

class SavingWalletView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), SavingWalletContact.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_wallet, context, viewGroup)

    private val mPresenter = SavingWalletPresenter(mvpActivity)
    private val listData = mutableListOf<ViewModel>()
    private val mResource = AddWalletResource()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

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
    private var dateChoose: String? = null
    private var yearChoose: String? = null


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

    override fun showListBank(list: MutableList<ViewModel>) {
        this.listBottom.clear()
        if (list.isNotEmpty()) {
            this.listBottom.addAll(list)
        }
        listViewBottom?.setItems(this.listBottom)
        listViewBottom?.notifyDataChanged()

        bottomSheet.show()
        bottomSheet.tvTitle.text = "Chọn ngân hàng"
    }

    private val onActionData = object : OnActionData<AddWalletTypeItemViewModel> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onAction(data: AddWalletTypeItemViewModel) {
            when (data.type) {
                AddWalletTypeItemViewModel.Type.SAVING -> {
                    mPresenter.getListBank()
                }
                AddWalletTypeItemViewModel.Type.START_DATE -> {
                    showBottomChooseDate()
                }
                AddWalletTypeItemViewModel.Type.PERIOD -> {
                    showBottomChooseYear()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showBottomChooseDate() {
        val customView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.item_choose_date, null, false)
        val dialog = BottomSheetDialog(mvpActivity)
        dialog.setContentView(customView)
        dialog.create()
        dialog.show()
        dialog.tvTitleChooseDate.text = "Chọn ngày bắt đầu"
        dialog.wpMonth.displayedValues = AppConstants.MONTH_IN_YEAR
        val monthOld: Int? = null
        val yearOld: Int? = null

        if (monthOld != null && yearOld != null) {
            dialog.wpMonth.value = monthOld
            dialog.wpYear.value = yearOld
        }

        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.tvSave.setOnClickListener {
            val day = dialog.wpDay.value
            val month = dialog.wpMonth.value
            val year = dialog.wpYear.value
            var result = ""
            result += "$day/$month/$year"
            dateChoose = result
            listData.forEach {
                if (it is AddWalletTypeItemViewModel && it.type == AddWalletTypeItemViewModel.Type.START_DATE) {
                    it.name = dateChoose
                    listViewMvp?.notifyItemChanged(listData.indexOf(it))
                }
            }
            dialog.dismiss()
        }
    }

    private fun getYear(value: String): String {
        return Utils.convertDateFormat(
            value,
            SimpleDateFormat("dd/MM/yyyy"),
            SimpleDateFormat("yyyy")
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showBottomChooseYear() {
        val customView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.item_choose_date, null, false)
        val dialog = BottomSheetDialog(mvpActivity)
        dialog.setContentView(customView)
        dialog.create()
        dialog.show()
        dialog.wpDay.gone()
        dialog.wpMonth.gone()
        dialog.tvTitleChooseDate.text = "Chọn năm kết thúc"
        dialog.tvTitleDay.gone()
        dialog.tvTitleMonthPicker.gone()

        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.tvSave.setOnClickListener {
            val year = dialog.wpYear.value
            val result = year.toString()
            yearChoose = result
            if (dateChoose != null) {
                if (getYear(dateChoose!!).toInt() < yearChoose!!.toInt()) {
                    listData.forEach {
                        if (it is AddWalletTypeItemViewModel && it.type == AddWalletTypeItemViewModel.Type.PERIOD) {
                            it.name = yearChoose
                            listViewMvp?.notifyItemChanged(listData.indexOf(it))
                        }
                    }
                    dialog.dismiss()
                } else {
                    showDialogNotify("Vui lòng chọn năm kì hạn lớn hơn năm bắt đầu")
                }
            } else {
                showDialogNotify("Vui lòng chọn ngày bắt đầu gửi tiết kiệm")
                dialog.dismiss()
            }
        }
    }

    private fun setDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun showDialogNotify(title: String? = null, isFinish: Boolean = false) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
            if(isFinish){
                mvpActivity.onBackPressed()
            }
        }
        if (!title.isNullOrEmpty()) {
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    private val onActionUpdateTotalPrice = object : OnActionData<AddWalletHeaderItemViewModel> {
        override fun onAction(data: AddWalletHeaderItemViewModel) {
            listData.forEach {
                if (it is AddWalletRateItemViewModel && it.isResult) {
                    it.price = data.price
                    listViewMvp?.notifyItemChanged(listData.indexOf(it))
                }
            }
        }
    }


    private val onActionSave = object : OnActionNotify {
        override fun onActionNotify() {
            // check data
            var price: Double? = null
            var name: String? = null
            var idBank: Int? = null
            listData.forEach {
                when (it) {
                    is AddWalletHeaderItemViewModel -> {
                        if (it.price.getValueOrDefaultIsZero() == 0.0) {
                            showDialogNotify("Bạn chưa nhập giá trị ban đầu")
                            return
                        } else {
                            price = it.price
                        }
                    }
                    is AddWalletNameItemViewModel -> {
                        if (it.name.isNullOrEmpty()) {
                            showDialogNotify("Bạn chưa nhập giá trị ban đầu")
                            return
                        } else {
                            name = it.name
                        }
                    }
                    is AddWalletTypeItemViewModel -> {
                        if (it.type == AddWalletTypeItemViewModel.Type.SAVING) {
                            if (it.id == null || it.id == 0) {
                                showDialogNotify("Bạn chưa chọn ngân hàng gửi tiết kiệm")
                                return
                            } else {
                                idBank = it.id
                            }
                        }
                    }
                }
            }
            if (dateChoose.isNullOrEmpty()) {
                showDialogNotify("Bạn chưa chọn ngày gửi tiết kiệm")
                return
            }

            if (yearChoose.isNullOrEmpty()) {
                showDialogNotify("Bạn chưa chọn kì hạn gửi")
                return
            }
            val data = ConfigUtil.passport
            if (data != null) {
                val request = WalletSavingRequest(
                    idUser = data.data.userId,
                    price = price.getValueOrDefaultIsZero(),
                    dateS = Utils.convertDateFormat(
                        dateChoose!!, SimpleDateFormat("dd/MM/yyyy"),
                        SimpleDateFormat("yyyy-MM-dd")
                    ),
                    dateE = "${yearChoose}-01-01",
                    idBank = idBank.getValueOrDefaultIsZero(),
                    nameSaving = name.getValueOrDefaultIsEmpty()
                )
                mPresenter.createWalletSaving(request)
            }
        }
    }

    override fun handleAfterCreate() {
        showDialogNotify("Bạn đã tạo thành công tài khoản tiết kiệm", true)
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private val onItemBankClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as BankItemViewModel
            listData.forEach {
                if (it is AddWalletTypeItemViewModel && it.type == AddWalletTypeItemViewModel.Type.SAVING) {
                    it.id = dataItem.id
                    it.name = dataItem.name
                }
                if (it is AddWalletRateItemViewModel && !it.isResult) {
                    it.rate = dataItem.interest
                }
                if (it is AddWalletRateItemViewModel && it.isResult) {
                    it.rate = dataItem.interest
                }
            }
            bottomSheet.dismiss()
            listViewMvp?.notifyDataChanged()
        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvItemAddWallet, renderConfig)
        listViewMvp?.addViewRenderer(
            AddWalletHeaderItemViewRenderer(
                mvpActivity,
                mResource,
                onActionUpdateTotalPrice
            )
        )
        listViewMvp?.addViewRenderer(AddWalletNameItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(AddWalletBottomItemViewRenderer(mvpActivity, onActionSave))
        listViewMvp?.addViewRenderer(EmptyLineViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddWalletRateItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(
            AddWalletTypeItemViewRenderer(
                mvpActivity,
                mResource,
                onActionData
            )
        )
        listViewMvp?.createView()

        bottomSheet.setContentView(customView)
        bottomSheet.create()
        listViewBottom = ListViewMvp(mvpActivity, bottomSheet.rvChoose, renderConfigBottom)
        listViewBottom?.addViewRenderer(BankItemViewRenderer(mvpActivity))
        listViewBottom?.setOnItemRvClickedListener(onItemBankClick)
        listViewBottom?.createView()
    }
}