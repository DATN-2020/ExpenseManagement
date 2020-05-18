package datn.datn_expansemanagement.screen.list_type_category.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.add_category.presentation.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.list_type_category.presentation.model.TypeCategoryItemViewModel
import datn.datn_expansemanagement.screen.list_type_category.presentation.renderer.TypeCategoryItemViewRenderer
import kotlinx.android.synthetic.main.layout_list_type_category.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ListTypeCategoryView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
private val type: TypeCategoryDataIntent? = null): AndroidMvpView(mvpActivity, viewCreator), ListTypeCategoryContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_list_type_category, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ListTypeCategoryPresenter()
    private val mResource = ListTypeCategoryResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    private val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel>{
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            val intent = Intent()
            dataItem as TypeCategoryItemViewModel
            intent.putExtra(TypeCategoryItemViewModel::class.java.simpleName, dataItem)
            mvpActivity.setResult(Activity.RESULT_OK, intent)
            mvpActivity.finish()
        }

    }
    override fun initCreateView() {
        view.tvToolbar.text = mResource.getTitleTypeCategory()
        view.imgAdd.gone()
        mvpActivity.setFullScreen()
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
        if(type != null){
            mPresenter.getData(type.id)
        }else{
            mPresenter.getData()
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

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvListTypeCategory, renderConfig)
        listViewMvp?.addViewRenderer(TypeCategoryItemViewRenderer(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(onItemRvClickedListener)
        listViewMvp?.createView()
    }

}