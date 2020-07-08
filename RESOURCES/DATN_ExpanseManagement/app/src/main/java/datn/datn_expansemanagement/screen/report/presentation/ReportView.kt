package datn.datn_expansemanagement.screen.report.presentation

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.GridRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report.presentation.renderer.*
import datn.datn_expansemanagement.view.custom_charts.CustomBarChart
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.layout_report.view.*
import kotlinx.android.synthetic.main.layout_report_receive.view.*
import kotlinx.android.synthetic.main.toolbar_account.view.*
import kotlinx.android.synthetic.main.toolbar_report.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ReportView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ReportContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportPresenter(AndroidScreenNavigator(mvpActivity))
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


    private val onItemClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as GetWalletItemViewModel

            listBottom.forEach {
                if(it is GetWalletItemViewModel){
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

    override fun initCreateView() {
        initRecycleView()
        initView()
    }

    private fun initView() {
        view.imgChooseDate.setOnClickListener {

        }

        view.imgChooseWallet.setOnClickListener {
            bottomSheet.show()
        }
    }


    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvReport, renderConfig)
        listViewMvp?.addViewRenderer(ReportBarChartViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(ReportPieChartViewRenderer(mvpActivity, mResource))
        listViewMvp?.addViewRenderer(ReportHeaderItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportNetIncomeViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportBalanceViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ReportBottomItemViewRenderer(mvpActivity))

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
        mPresenter.getData()
//        mPresenter.getWalletForUser(null)
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