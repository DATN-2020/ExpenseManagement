package datn.datn_expansemanagement.screen.add_plan.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanCategoryViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanDateViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanPriceViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.model.AddPlanWalletViewModel
import datn.datn_expansemanagement.screen.add_plan.presentation.renderer.AddPlanCategoryViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.renderer.AddPlanDateViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.renderer.AddPlanPriceViewRenderer
import datn.datn_expansemanagement.screen.add_plan.presentation.renderer.AddPlanWalletViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel
import kotlinx.android.synthetic.main.layout_add_plan.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddPlanView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val typeAdd: TypeAddViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), AddPlanContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_plan, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = AddPlanPresenter()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        initRecycleView()
        mvpActivity.setFullScreen()
        view.tvToolbar.text = "Thêm ngân sách"
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgSave.setOnClickListener {
            var isSuccess =  true
            listData.forEach {
                when(it){
                    is AddPlanCategoryViewModel->{
                        if(it.id == null){
                            showError("Bạn chưa chọn loại áp dụng")
                            isSuccess = false
                            return@forEach
                        }
                    }
                    is AddPlanDateViewModel ->{
                        if(it.id == null){
                            showError("Bạn chưa chọn thời gian áp dụng")
                            isSuccess = false
                            return@forEach
                        }
                    }
                    is AddPlanPriceViewModel ->{
                        if(it.price == null && it.price == 0.0){
                            showError("Bạn chưa nhập mục tiêu áp dụng")
                            isSuccess = false
                            return@forEach
                        }
                    }
                    is AddPlanWalletViewModel ->{
                        if(it.id == null){
                            showError("Bạn chưa chọn ví áp dụng")
                            isSuccess = false
                            return@forEach
                        }
                    }
                }
            }

            if(isSuccess){
                mvpActivity.onBackPressed()
            }
        }
    }

    private fun showError(message: String){
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
        typeAdd?.let { mPresenter.getData(it) }
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
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddPlan, renderConfig)
        listViewMvp?.addViewRenderer(AddPlanWalletViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddPlanDateViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddPlanPriceViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(AddPlanCategoryViewRenderer(mvpActivity))
        listViewMvp?.createView()
    }

}