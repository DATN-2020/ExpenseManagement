package datn.datn_expansemanagement.screen.add_expanse.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expanse.presentation.renderer.AddExpenseRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.AddExpenseDonateFragment
import datn.datn_expansemanagement.screen.add_expense_receive.AddExpenseReceiveFragment
import kotlinx.android.synthetic.main.layout_add_expanse.view.*
import kotlinx.android.synthetic.main.toolbar_add_expanse.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddExpenseView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expanse, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = AddExpensePresenter()
    private val mResource = AddExpenseResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private var isOpen = false
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val onClickTypeExpense = object : OnActionData<AddExpenseViewModel>{
        override fun onAction(data: AddExpenseViewModel) {
            listData.forEach {
                if(it is AddExpenseViewModel){
                    if(it.type == data.type){
                       if(!it.isChoose){
                           when(data.type){
                               AddExpenseViewModel.Type.RECEIVE ->{
                                   replaceFragment(AddExpenseReceiveFragment())
                                   view.tvToolbar.text = mResource.getTextReceive()
                               }
                               AddExpenseViewModel.Type.DONATE->{
                                   replaceFragment(AddExpenseDonateFragment())
                                   view.tvToolbar.text = mResource.getTextDonate()
                               }
                           }
                           it.isChoose = true
                       }
                    }else{
                        it.isChoose = false
                    }
                }
            }

            view.cvType.gone()
            isOpen = false
            listViewMvp?.setItems(listData)
            listViewMvp?.notifyDataChanged()
        }

    }

    override fun initCreateView() {
        initView()
        initRecycleView()
        replaceFragment(AddExpenseDonateFragment())
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

    private fun replaceFragment(frm: Fragment) {
        mvpActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flChange, frm)
            .commit()
    }

    private fun initView(){
        view.cvType.gone()
        view.tvToolbar.text = mResource.getTextDonate()
        view.imgChooseType.setOnClickListener {
            isOpen = if(!isOpen){
                view.cvType.visible()
                true
            }else{
                view.cvType.gone()
                false
            }
        }
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvTypeExpense, renderConfig)
        listViewMvp?.addViewRenderer(AddExpenseRenderer(mvpActivity, mResource, onClickTypeExpense))
        listViewMvp?.createView()
    }
}