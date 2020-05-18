package datn.datn_expansemanagement.screen.category.item_category.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.TrimSign
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemCategoryViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.renderer.ItemCategoryViewRenderer
import kotlinx.android.synthetic.main.layout_item_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ItemCategoryView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val tabId: Int?,
    private val itemId: Int? = null
) : AndroidMvpView(mvpActivity, viewCreator), ItemCategoryContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_item_category, context, viewGroup)

    private val mPresenter = ItemCategoryPresenter()
    private val mResource = ItemCategoryResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private var keyQuery: String? = null

    private val onChooseCategory = object : OnActionData<ItemCategoryViewModel>{
        override fun onAction(data: ItemCategoryViewModel) {
            val intent = Intent()
            intent.putExtra(ItemCategoryViewModel::class.java.simpleName, data)
            mvpActivity.setResult(Activity.RESULT_OK, intent)
            mvpActivity.finish()
        }

    }

    override fun initCreateView() {
        initRecycleView()
        initSearchView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if(list.isNotEmpty()){
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvCategory, renderConfig)
        listViewMvp?.addViewRenderer(ItemCategoryViewRenderer(mvpActivity, onChooseCategory))
        listViewMvp?.createView()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData(tabId.getValueOrDefaultIsZero(), itemId)
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    private fun initSearchView() {
        val v = view.svCategory.findViewById(androidx.appcompat.R.id.search_plate) as View
        view.svCategory.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                keyQuery = query
                searchData()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                keyQuery = newText
                searchData()
                return true
            }

        })
    }

    private fun searchData() {
        val listFilter = if (keyQuery.isNullOrEmpty()) {
            listData
        } else {
            searchList()
        }
        listViewMvp?.setItems(listFilter)
        listViewMvp?.notifyDataChanged()
    }

    private fun searchList(): MutableList<ViewModel> {
        val trimSearch = if (!keyQuery.isNullOrEmpty()) {
            TrimSign.getInstances(mvpActivity).unicodeTrimSign(keyQuery.getValueOrDefaultIsEmpty())
                .toLowerCase()
        } else {
            ""
        }
        return listData.filter {
            if (it is ItemCategoryViewModel) {
                if (trimSearch.isNotEmpty()) {
                    TrimSign.getInstances(mvpActivity).unicodeTrimSign(it.name)
                        .contains(trimSearch, true)
                } else {
                    false
                }
            } else false
        }.toMutableList()
    }
}