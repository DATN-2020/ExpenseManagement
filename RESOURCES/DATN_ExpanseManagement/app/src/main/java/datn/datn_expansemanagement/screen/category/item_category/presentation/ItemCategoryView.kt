package datn.datn_expansemanagement.screen.category.item_category.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.TrimSign
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.CategoryItemViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemTypeCategoryViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.renderer.TypeCategoryItemViewRenderer
import kotlinx.android.synthetic.main.layout_item_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ItemCategoryView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val tabName: String?,
    private val idCategory: Int? = null,
    private val isPlan: Boolean? = false
) : AndroidMvpView(mvpActivity, viewCreator), ItemCategoryContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_item_category, context, viewGroup)

    private val mPresenter = ItemCategoryPresenter(mvpActivity)
    private val mResource = ItemCategoryResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private var keyQuery: String? = null

    private val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as ItemTypeCategoryViewModel
            if(isPlan == true){
                val intent = Intent()
                val typeIntent = TypeCategoryDataIntent(id = dataItem.id.getValueOrDefaultIsZero(), name = dataItem.name.getValueOrDefaultIsEmpty())
                intent.putExtra("isType", true)
                intent.putExtra(TypeCategoryDataIntent::class.java.simpleName, typeIntent)
                mvpActivity.setResult(Activity.RESULT_OK, intent)
                mvpActivity.finish()
            }else{
                val model = AddExpenseViewModel.Info.Category(
                    id = dataItem.id.getValueOrDefaultIsZero(),
                    name = dataItem.name.getValueOrDefaultIsEmpty(),
                    isTypeCategory = true
                )
                AddExpenseFragment.model.category = model
                mvpActivity.setResult(Activity.RESULT_OK)
                mvpActivity.finish()
            }
        }
    }

    private val onActionShow = object : OnActionData<ItemTypeCategoryViewModel>{
        override fun onAction(data: ItemTypeCategoryViewModel) {
            data.isShowChill = !data.isShowChill
            listViewMvp?.notifyDataChanged()
        }

    }

    private val onChooseChild = object : OnActionData<ViewModel> {
        override fun onAction(data: ViewModel) {
            data as CategoryItemViewModel
            if(isPlan == true){
                val intent = Intent()
                intent.putExtra(CategoryItemViewModel::class.java.simpleName, data)
                intent.putExtra("isType", false)
                mvpActivity.setResult(Activity.RESULT_OK, intent)
                mvpActivity.finish()
            }else{
                val model = AddExpenseViewModel.Info.Category(
                    id = data.id.getValueOrDefaultIsZero(),
                    name = data.name.getValueOrDefaultIsEmpty()
                )
                AddExpenseFragment.model.category = model
                mvpActivity.setResult(Activity.RESULT_OK)
                mvpActivity.finish()
            }

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
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvCategory, renderConfig)
        listViewMvp?.addViewRenderer(TypeCategoryItemViewRenderer(mvpActivity, onChooseChild, onActionShow))
        listViewMvp?.setOnItemRvClickedListener(onItemRvClickedListener)
        listViewMvp?.createView()
    }

    override fun initData() {
        super.initData()
        if(isPlan == true){
            mPresenter.getData(
                tabName.getValueOrDefaultIsEmpty(),
                idCategory
            )
        }else{
            mPresenter.getData(
                tabName.getValueOrDefaultIsEmpty(),
                AddExpenseFragment.model.category?.id.getValueOrDefaultIsZero()
            )
        }
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
            if (it is ItemTypeCategoryViewModel) {
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