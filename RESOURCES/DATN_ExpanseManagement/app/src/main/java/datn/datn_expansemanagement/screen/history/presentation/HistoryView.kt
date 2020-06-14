package datn.datn_expansemanagement.screen.history.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.history.presentation.renderer.HistoryDateItemViewRenderer
import datn.datn_expansemanagement.screen.history.presentation.renderer.HistoryItemViewRenderer
import datn.datn_expansemanagement.screen.history.presentation.renderer.HistoryTotalItemViewRenderer
import kotlinx.android.synthetic.main.layout_history.view.*
import kotlinx.android.synthetic.main.toolbar_history.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*

class HistoryView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), HistoryContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_history, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    private val mPresenter = HistoryPresenter()
    private val mResource = HistoryResource()
    private var isSearch: Boolean = false

    private val onActionClick = View.OnClickListener {
        when (it.id) {
            view.imgSearch.id -> {
                showSearch()
            }
            view.clDateFrom.id -> {
                showCalendar(view.tvDateFrom)
            }
            view.clDateTo.id -> {
                showCalendar(view.tvDateTo)
            }
        }
    }

    private fun getCurrentDate(): String {
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }

    private fun showCalendar(v: TextView) {
        val datePickerDialog = DatePickerDialog(mvpActivity)
        datePickerDialog.show()
        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            val date = String.format("%02d/%02d/%d ", dayOfMonth, month + 1, year)
            v.text = date
        }
    }

    private fun showSearch() {
        if (!isSearch) {
            isSearch = true
            GlideImageHelper(mvpActivity).loadThumbnail(
                view.imgSearch,
                R.drawable.ic_close_white_24dp,
                R.drawable.ic_close_white_24dp
            )
            view.svHistory.visible()
            view.tvToolbar.invisible()
        } else {
            isSearch = false
            GlideImageHelper(mvpActivity).loadThumbnail(
                view.imgSearch,
                R.drawable.ic_search_black_24dp,
                R.drawable.ic_search_black_24dp
            )
            view.svHistory.gone()
            view.tvToolbar.visible()
        }

    }

    private fun initView() {
        view.imgSearch.setOnClickListener(onActionClick)
        view.clDateFrom.setOnClickListener(onActionClick)
        view.clDateTo.setOnClickListener(onActionClick)
    }

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        initView()
        initRecycleView()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
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

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvHistory, renderConfig)
        listViewMvp?.addViewRenderer(HistoryTotalItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(HistoryDateItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(HistoryItemViewRenderer(mvpActivity, mResource))
        listViewMvp?.createView()
    }
}