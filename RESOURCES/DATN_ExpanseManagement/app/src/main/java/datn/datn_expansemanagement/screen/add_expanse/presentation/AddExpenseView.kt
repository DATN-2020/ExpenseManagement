package datn.datn_expansemanagement.screen.add_expanse.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.data.AddDonateDataBus
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expanse.presentation.renderer.AddExpenseRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.AddExpenseDonateFragment
import datn.datn_expansemanagement.screen.add_expense_receive.AddExpenseReceiveFragment
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.layout_add_expanse.view.*
import kotlinx.android.synthetic.main.toolbar_add_expanse.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddExpenseView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expanse, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = AddExpensePresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    private val mResource = AddExpenseResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private var isOpen = false
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

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
                           view.clBackground.gone()
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
        addLifeCycle(eventBusLifeCycle)
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

    private fun setDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun handleAfterRegister() {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
        }
        view.imgNotify.visible()
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
                view.clBackground.visible()
                true
            }else{
                view.cvType.gone()
                view.clBackground.gone()
                false
            }
        }
        view.imgAdd.setOnClickListener {
            handleAfterRegister()
        }
        view.clBackground.setOnClickListener {
            view.clBackground.gone()
            view.cvType.gone()
        }

        view.imgHistory.setOnClickListener {
            view.imgNotify.gone()
            mPresenter.gotoHistoryActivity()
        }
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvTypeExpense, renderConfig)
        listViewMvp?.addViewRenderer(AddExpenseRenderer(mvpActivity, mResource, onClickTypeExpense))
        listViewMvp?.createView()
    }
}