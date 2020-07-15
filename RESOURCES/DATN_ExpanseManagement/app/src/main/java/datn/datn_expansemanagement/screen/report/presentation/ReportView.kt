package datn.datn_expansemanagement.screen.report.presentation

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.common.AppConstants
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report.presentation.renderer.*
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailItemViewModel
import datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer.ReportDetailItemViewRenderer
import datn.datn_expansemanagement.view.numberpicker.NumberPicker
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.layout_choose_date_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_report.view.*
import kotlinx.android.synthetic.main.toolbar_report.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*

class ReportView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val idWallet: Int? = null,
    private val isCardWallet: Boolean = false
) :
    AndroidMvpView(mvpActivity, viewCreator), ReportContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter =
        ReportPresenter(AndroidScreenNavigator(mvpActivity), mvpActivity = mvpActivity)
    private val mResource = ReportResource(mvpActivity)
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

    private var dateChoose: String? = null


    private val onItemClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(v: View, position: Int, dataItem: ViewModel) {
            dataItem as GetWalletItemViewModel

            listBottom.forEach {
                if (it is GetWalletItemViewModel) {
                    it.isChoose = false
                }
            }
            dataItem.isChoose = true

            view.tvWalletName.text = dataItem.name
            view.tvPriceWallet.text = Utils.formatMoney(dataItem.money)
            // gọi api đổ lại list report
            listViewBottom?.notifyDataChanged()
            bottomSheet.dismiss()
        }

    }

    private val onActionChart = object : OnActionNotify {
        override fun onActionNotify() {
            val data = ReportViewModel(
                date = dateChoose
            )
            mPresenter.gotoReportDetailActivity(data)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initCreateView() {
        initRecycleView()
        initView()
        view.tvMonth.text = if (dateChoose.isNullOrEmpty()) {
            getCurrentMonth()
        } else {
            dateChoose
        }
    }

    private fun getCurrentMonth(): String {
        val dateFormat = SimpleDateFormat("MM/yyyy")
        val date = Date()
        return dateFormat.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        view.imgChooseDate.setOnClickListener {
            showBottomChooseDate()
        }

        view.imgChooseWallet.setOnClickListener {
            bottomSheet.show()
        }
    }

    private fun handleActionMonth(
        monthPicker: NumberPicker,
        dayPicker: NumberPicker,
        yearPicker: NumberPicker
    ) {
        monthPicker.setOnValueChangedListener { _, _, newVal ->
            checkLogicDate(AppConstants.MONTH_IN_YEAR[newVal - 1], dayPicker, yearPicker)
        }
    }

    private fun checkLogicDate(
        monthValue: String,
        dayPicker: NumberPicker,
        yearPicker: NumberPicker
    ) {
        val valueMax: Int = if (AppConstants.MONTH_31_DAYS.contains(monthValue)) {
            31
        } else if (AppConstants.MONTH_30_DAYS.contains(monthValue)) {
            30
        } else {
            if (checkLeapYear(yearPicker.value)) {
                29
            } else {
                28
            }
        }
        if (dayPicker.value > valueMax) {
            dayPicker.value = valueMax
        }
        dayPicker.maxValue = valueMax
        dayPicker.requestLayout()
    }

    private fun checkLeapYear(year: Int): Boolean {
        if (year % 400 == 0) {
            return true
        } else if (year % 4 == 0 && year % 100 != 0) {
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showBottomChooseDate() {
        val customView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.layout_choose_date_bottom_sheet, null, false)
        val dialog = BottomSheetDialog(mvpActivity)
        dialog.setContentView(customView)
        dialog.create()
        dialog.show()
        dialog.wpMonth.displayedValues = AppConstants.MONTH_IN_YEAR
        val monthOld: Int? = null
        val yearOld: Int? = null

        if (monthOld != null && yearOld != null) {
            dialog.wpMonth.value = monthOld as Int
            dialog.wpYear.value = yearOld as Int
        }

        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.tvSave.setOnClickListener {
            val month = dialog.wpMonth.value
            val year = dialog.wpYear.value
            var result = ""
            result += "$month/$year"
            dateChoose = result
            view.tvMonth.text = dateChoose

            // call api
            dialog.dismiss()
        }
    }

    private val showTransaction = object : OnActionNotify {
        override fun onActionNotify() {
            val customViewTransaction = LayoutInflater.from(mvpActivity)
                .inflate(R.layout.custom_bottomsheet_recycleview, null, false)
            val bottomSheet = BottomSheetDialog(mvpActivity)
            bottomSheet.setContentView(customViewTransaction)
            bottomSheet.show()

            val list = mutableListOf<ViewModel>()
            var viewMvp: ListViewMvp? = null

            val input = LinearRenderConfigFactory.Input(
                context = mvpActivity,
                orientation = LinearRenderConfigFactory.Orientation.VERTICAL
            )
            val config = LinearRenderConfigFactory(input).create()
            viewMvp = ListViewMvp(mvpActivity, bottomSheet.rvChoose, config)
            viewMvp.addViewRenderer(ReportDetailItemViewRenderer(mvpActivity))
            viewMvp.createView()

            for (i in 1..2) {
                list.add(
                    ReportDetailItemViewModel(
                        name = "Đi lại",
                        price = 300000.0
                    )
                )
            }

            viewMvp.setItems(list)
            viewMvp.notifyDataChanged()

            bottomSheet.tvTitle.text = "Lịch sử giao dịch"
        }

    }


    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvReport, renderConfig)
        listViewMvp?.addViewRenderer(ReportBarChartViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(
            ReportPieChartViewRenderer(
                mvpActivity,
                mResource,
                onActionChart
            )
        )
        listViewMvp?.addViewRenderer(ReportHeaderItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportNetIncomeViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportBalanceViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportBottomItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportHeaderCardViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportProcessCardViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportBottomCardViewRenderer(mvpActivity, showTransaction))

        listViewMvp?.createView()

        bottomSheet.setContentView(customView)
        bottomSheet.create()
        listViewBottom = ListViewMvp(mvpActivity, bottomSheet.rvChoose, renderConfigBottom)
        listViewBottom?.addViewRenderer(GetWalletItemViewRenderer(mvpActivity))
        listViewBottom?.setOnItemRvClickedListener(onItemClick)
        listViewBottom?.createView()
    }

    override fun showLoading() {
        loadingView.show()
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

    override fun handleAfterGetWallet(list: MutableList<ViewModel>) {
        this.listBottom.clear()
        if (list.isNotEmpty()) {
            this.listBottom.addAll(list)
        }

        listViewBottom?.setItems(this.listBottom)
        listViewBottom?.notifyDataChanged()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData(idWallet, isCardWallet)
        mPresenter.getWalletForUser(idWallet)
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
}