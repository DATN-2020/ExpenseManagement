package datn.datn_expansemanagement.screen.exchange_rate.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
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
import datn.datn_expansemanagement.screen.exchange_rate.presentation.renderer.ExchangeRateViewRenderer
import kotlinx.android.synthetic.main.layout_exchange_rate.view.*
import kotlinx.android.synthetic.main.toolbar_history.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ExchangeRateView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), ExchangeRateContract.View{

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_exchange_rate, context, viewGroup)

    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val mPresenter = ExchangeRatePresenter(mvpActivity)
    private val mResource = ExchangeRateResource()
    private var isSearch: Boolean = false

    private val onActionClick = View.OnClickListener {
        when (it.id) {
            view.imgSearch.id -> {
                showSearch()
            }
            view.imgBack.id->{
                mvpActivity.onBackPressed()
            }
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

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        initView()
        initRecycleView()
    }

    private fun initView(){
        view.imgSearch.setOnClickListener(onActionClick)
        view.tvToolbar.text = mResource.getTextTitle()
        view.svHistory.queryHint = mResource.getHintTitle()
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

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvExchangeRate, renderConfig)
        listViewMvp?.addViewRenderer(ExchangeRateViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }
}