package datn.datn_expansemanagement.screen.add_plan.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.change_screen.Request
import datn.datn_expansemanagement.core.app.util.DateTimeFormat
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.domain.request.AddBudgetRequest
import datn.datn_expansemanagement.domain.request.BillRequest
import datn.datn_expansemanagement.domain.request.TransactionRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.add_plan.presentation.model.*
import datn.datn_expansemanagement.screen.add_plan.presentation.renderer.*
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.CategoryItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.renderer.GetWalletItemViewRenderer
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.custom_date_range_picker.view.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.layout_add_plan.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AddPlanView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val typeAdd: PlanItemViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), AddPlanContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_plan, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter =
        AddPlanPresenter(AndroidScreenNavigator(mvpActivity), mvpActivity = mvpActivity)
    private val listData = mutableListOf<ViewModel>()
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
    private val resourceProvider = AddPlanResource()

    private val viewTimePicker = LayoutInflater.from(mvpActivity)
        .inflate(R.layout.custom_date_range_picker, null, false)
    private val datePicker =
        AlertDialog.Builder(mvpActivity).setView(viewTimePicker).create()

    private val onChooseTime = object : OnActionData<AddPlanDateViewModel> {
        override fun onAction(data: AddPlanDateViewModel) {
            mPresenter.getTime()
            bottomSheet.show()
            bottomSheet.tvTitle.text = "Chọn thời gian lặp lại"
        }

    }

    private val mResource = AddPlanResource()
    private fun setDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun showDialogNotify(title: String? = null, titleClose: String? = null) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.text = titleClose
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
            mvpActivity.onBackPressed()
        }
        if (!title.isNullOrEmpty()) {
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    private fun initDatePicker() {
        viewTimePicker.datePicker.setCalendarListener(object :
            DateRangeCalendarView.CalendarListener {
            override fun onDateRangeSelected(startDate: Calendar?, endDate: Calendar?) {
                startDate?.let {
                    val dayInWeek = startDate.get(Calendar.DAY_OF_WEEK)
                    val day = resourceProvider.getDay(dayInWeek)
                    val date =
                        SimpleDateFormat(DateTimeFormat.DDMMYYYY.format).format(startDate.time)
                    viewTimePicker.tvCheckInDate.text =
                        resourceProvider.getDateCheckFormat(day, date)
                }

                endDate?.let {
                    val dayInWeek = endDate.get(Calendar.DAY_OF_WEEK)
                    val day = resourceProvider.getDay(dayInWeek)
                    val date = SimpleDateFormat(DateTimeFormat.DDMMYYYY.format).format(endDate.time)
                    viewTimePicker.tvCheckOutDate.text =
                        resourceProvider.getDateCheckFormat(day, date)
                }

                if (startDate != null && endDate != null) {
                    val msDiff = endDate.timeInMillis - startDate.timeInMillis
                    val dayDiff = TimeUnit.MILLISECONDS.toDays(msDiff)
                    viewTimePicker.tvTotalDate.text =
                        resourceProvider.getDateTotal(dayDiff.toInt() + 1)
                    listData.forEach {
                        if (it is AddPlanChooseDateViewModel) {
                            it.startDate = getDate(viewTimePicker.tvCheckInDate.text.toString())
                            it.endDate = getDate(viewTimePicker.tvCheckOutDate.text.toString())
                        }
                    }
                }
            }

            override fun onFirstDateSelected(startDate: Calendar?) {
                startDate?.let {
                    val dayInWeek = startDate.get(Calendar.DAY_OF_WEEK)
                    val day = resourceProvider.getDay(dayInWeek)
                    val date =
                        SimpleDateFormat(DateTimeFormat.DDMMYYYY.format).format(startDate.time)
                    viewTimePicker.tvCheckInDate.text =
                        resourceProvider.getDateCheckFormat(day, date)
                }

                viewTimePicker.tvCheckOutDate.text = resourceProvider.getDateCheckOut()
            }
        })

        viewTimePicker.btnSave.setOnClickListener {
            datePicker.dismiss()
            listViewMvp?.notifyDataChanged()
        }
    }

    fun getDate(date: String): String {
        val result = date.split(',')
        return result.last().trim()
    }

    private val onChooseDate = object : OnActionData<AddPlanChooseDateViewModel> {
        override fun onAction(data: AddPlanChooseDateViewModel) {
            datePicker.show()
        }
    }

    override fun initCreateView() {
        initDatePicker()
        initRecycleView()
        mvpActivity.setFullScreen()
        when (typeAdd?.type) {
            PlanItemViewModel.Type.BUDGET -> {
                view.tvToolbar.text = "Thêm ngân sách"
            }
            PlanItemViewModel.Type.TRANSACTION -> {
                view.tvToolbar.text = "Thêm giao dịch định kì"
            }
            else -> {
                view.tvToolbar.text = "Thêm hoá đơn"
            }
        }

        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgSave.setOnClickListener {
            var isSuccess = true
            var price = 0.0
            var idTime: String? = null
            var idWallet: Int? = null
            var idCate: String? = null
            var idTypeCate: String? = null
            var startDate: String? = null
            var endDate: String? = null
            var des: String? = null

            listData.forEach {
                when (it) {
                    is AddPlanCategoryViewModel -> {
                        if (it.id == null) {
                            showError("Bạn chưa chọn loại áp dụng")
                            isSuccess = false
                            return@forEach
                        } else {
                            if (it.isType) {
                                idTypeCate = it.id.toString()
                            } else {
                                idCate = it.id.toString()
                            }
                        }
                    }
                    is AddPlanChooseDateViewModel -> {
                        if (it.startDate.isNullOrEmpty() && it.endDate.isNullOrEmpty()) {
                            showError("Bạn chưa chọn thời gian áp dụng")
                            isSuccess = false
                            return@forEach
                        } else {
                            startDate = it.startDate
                            endDate = it.endDate
                        }
                    }
                    is AddPlanDateViewModel -> {
                        if (it.id == null) {
                            showError("Bạn chưa chọn thời gian lặp")
                            isSuccess = false
                            return@forEach
                        } else {
                            idTime = it.id.toString()
                        }
                    }
                    is AddPlanPriceViewModel -> {
                        if (it.price == null && it.price == 0.0) {
                            showError("Bạn chưa nhập mục tiêu áp dụng")
                            isSuccess = false
                            return@forEach
                        } else {
                            price = it.price.getValueOrDefaultIsZero()
                        }
                    }
                    is AddPlanWalletViewModel -> {
                        if (it.id == null) {
                            showError("Bạn chưa chọn ví áp dụng")
                            isSuccess = false
                            return@forEach
                        } else {
                            idWallet = it.id
                        }
                    }
                    is AddPlanDesViewModel -> {
                        des = it.des
                    }
                }
            }

            if (isSuccess) {
                when (typeAdd?.type) {
                    PlanItemViewModel.Type.BUDGET -> {
                        val request = AddBudgetRequest(
                            amountBudget = price.getValueOrDefaultIsZero(),
                            idWallet = idWallet.getValueOrDefaultIsZero().toString(),
                            idCate = idCate,
                            idType = idTypeCate,
                            timeE = Utils.convertDateFormat(
                                endDate.getValueOrDefaultIsEmpty(),
                                SimpleDateFormat("dd/MM/yyyy"),
                                SimpleDateFormat("yyyy-MM-dd")
                            ),
                            timeS = Utils.convertDateFormat(
                                startDate.getValueOrDefaultIsEmpty(),
                                SimpleDateFormat("dd/MM/yyyy"),
                                SimpleDateFormat("yyyy-MM-dd")
                            ),
                            idTime = idTime.getValueOrDefaultIsEmpty()
                        )
                        mPresenter.addBudget(request = request)
                    }
                    PlanItemViewModel.Type.TRANSACTION -> {
                        val request = TransactionRequest(
                            amountPer = price.getValueOrDefaultIsZero(),
                            idWallet = idWallet.getValueOrDefaultIsZero().toString(),
                            idCate = if (idCate != null) idCate else null,
                            idType = if (idTypeCate != null) idTypeCate else null,
                            dateE = Utils.convertDateFormat(
                                endDate.getValueOrDefaultIsEmpty(),
                                SimpleDateFormat("dd/MM/yyyy"),
                                SimpleDateFormat("yyyy-MM-dd")
                            ),
                            dateS = Utils.convertDateFormat(
                                startDate.getValueOrDefaultIsEmpty(),
                                SimpleDateFormat("dd/MM/yyyy"),
                                SimpleDateFormat("yyyy-MM-dd")
                            ),
                            idTime = idTime.getValueOrDefaultIsEmpty(),
                            desciption = des.getValueOrDefaultIsEmpty()
                        )
                        mPresenter.addTransaction(request = request)
                    }
                    else->{
                        val request = BillRequest(
                            amountBill = price.getValueOrDefaultIsZero(),
                            idWallet = idWallet.getValueOrDefaultIsZero().toString(),
                            idCategory = if (idCate != null) idCate else null,
                            idType = if (idTypeCate != null) idTypeCate else null,
                            dateE = Utils.convertDateFormat(
                                endDate.getValueOrDefaultIsEmpty(),
                                SimpleDateFormat("dd/MM/yyyy"),
                                SimpleDateFormat("yyyy-MM-dd")
                            ),
                            dateS = Utils.convertDateFormat(
                                startDate.getValueOrDefaultIsEmpty(),
                                SimpleDateFormat("dd/MM/yyyy"),
                                SimpleDateFormat("yyyy-MM-dd")
                            ),
                            idTime = idTime.getValueOrDefaultIsEmpty(),
                            desciption = des.getValueOrDefaultIsEmpty()
                        )
                        mPresenter.addBill(request = request)
                    }
                }
            }
        }
    }

    override fun handleAfterAddTransaction() {
        showDialogNotify(
            "Bạn đã tạo thành công cho mình một giao dịch định kỳ mới",
            titleClose = "Quay lại giao dịch định kỳ"
        )
    }

    override fun handleAfterAddBill() {
        showDialogNotify(
            "Bạn đã tạo thành công cho mình một hóa đơn mới",
            titleClose = "Quay lại hóa đơn"
        )
    }

    private fun showError(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
//        typeAdd?.let { mPresenter.getData(it) }
        mPresenter.getData(typeAdd)
        mPresenter.getTime()
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

    override fun showListTime(list: MutableList<ViewModel>) {
        this.listBottom.clear()
        if (list.isNotEmpty()) {
            this.listBottom.addAll(list)
        }

        listViewBottom?.setItems(this.listBottom)
        listViewBottom?.notifyDataChanged()
    }

    private val onItemClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            if (dataItem is GetWalletItemViewModel) {
                listData.forEach {
                    if (it is AddPlanWalletViewModel) {
                        it.id = dataItem.id
                        it.name = dataItem.name
                        listViewMvp?.notifyItemChanged(listData.indexOf(it))
                        bottomSheet.dismiss()
                        return
                    }
                }
            } else {
                dataItem as TimeItemViewModel
                listData.forEach {
                    if (it is AddPlanDateViewModel) {
                        it.id = dataItem.id
                        it.date = dataItem.name
                        listViewMvp?.notifyItemChanged(listData.indexOf(it))
                        bottomSheet.dismiss()
                        return
                    }
                }
            }
        }

    }

    private val onChooseCategory = object : OnActionData<AddPlanCategoryViewModel> {
        override fun onAction(data: AddPlanCategoryViewModel) {
            mPresenter.gotoCategoryActivity(data.id)
        }

    }

    private val onChooseWallet = object : OnActionData<AddPlanWalletViewModel> {
        override fun onAction(data: AddPlanWalletViewModel) {
            mPresenter.getWalletForUser(data.id.getValueOrDefaultIsZero())
        }

    }

    override fun handleAfterGetWallet(list: MutableList<ViewModel>) {
        listBottom.clear()
        if (list.isNotEmpty()) {
            listBottom.addAll(list)
        }
        listViewBottom?.setItems(listBottom)
        listViewBottom?.notifyDataChanged()
        bottomSheet.show()
    }

    override fun handleAfterAddBudget() {
        showDialogNotify(
            title = "Bạn đã tạo thành công cho mình một ngân sách mới",
            titleClose = "Quay lại ngân sách"
        )
    }

    override fun onViewResult(viewResult: ViewResult) {
        super.onViewResult(viewResult)
        when (viewResult.requestCode) {
            Request.REQUEST_CODE_CATEGORY -> {
                val isType = viewResult.data?.getBooleanExtra("isType", false)
                if (isType == true) {
                    val data = viewResult.data.getParcelableExtra<TypeCategoryDataIntent>(
                        TypeCategoryDataIntent::class.java.simpleName
                    )

                    if (data != null) {
                        listData.forEach {
                            if (it is AddPlanCategoryViewModel) {
                                it.id = data.id.getValueOrDefaultIsZero()
                                it.name = data.name.getValueOrDefaultIsEmpty()
                                it.isType = true
                                listViewMvp?.notifyItemChanged(listData.indexOf(it))
                            }
                        }
                    }
                } else {
                    val data =
                        viewResult.data?.getParcelableExtra<CategoryItemViewModel>(
                            CategoryItemViewModel::class.java.simpleName
                        )

                    if (data != null) {
                        listData.forEach {
                            if (it is AddPlanCategoryViewModel) {
                                it.id = data.id.getValueOrDefaultIsZero()
                                it.name = data.name.getValueOrDefaultIsEmpty()
                                it.isType = false
                                listViewMvp?.notifyItemChanged(listData.indexOf(it))
                            }
                        }
                    }
                }


            }
        }
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddPlan, renderConfig)
        listViewMvp?.addViewRenderer(AddPlanWalletViewRenderer(mvpActivity, onChooseWallet))
        listViewMvp?.addViewRenderer(AddPlanDateViewRenderer(mvpActivity, onChooseTime))
        listViewMvp?.addViewRenderer(AddPlanPriceViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddPlanChooseDateViewRenderer(mvpActivity, onChooseDate))
        listViewMvp?.addViewRenderer(AddPlanCategoryViewRenderer(mvpActivity, onChooseCategory))
        listViewMvp?.addViewRenderer(AddPlanDesViewRenderer(mvpActivity))
        listViewMvp?.createView()

        bottomSheet.setContentView(customView)
        bottomSheet.create()
        listViewBottom = ListViewMvp(mvpActivity, bottomSheet.rvChoose, renderConfigBottom)
        listViewBottom?.addViewRenderer(TimeItemViewRenderer(mvpActivity))
        listViewBottom?.addViewRenderer(GetWalletItemViewRenderer(mvpActivity))
        listViewBottom?.setOnItemRvClickedListener(onItemClick)
        listViewBottom?.createView()
    }

}