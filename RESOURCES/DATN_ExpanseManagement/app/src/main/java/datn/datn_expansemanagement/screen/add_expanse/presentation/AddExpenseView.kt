package datn.datn_expansemanagement.screen.add_expanse.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.domain.request.InOutComeRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.data.TransactionDataBus
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expanse.presentation.renderer.AddExpenseRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.AddExpenseDonateFragment
import datn.datn_expansemanagement.screen.add_expense_loan.AddExpenseLoanFragment
import kotlinex.view.hideKeyboard
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.layout_add_expanse.view.*
import kotlinx.android.synthetic.main.toolbar_add_expanse.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expanse, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter =
        AddExpensePresenter(
            screenNavigator = AndroidScreenNavigator(mvpActivity),
            mvpActivity = mvpActivity
        )
    private val mResource = AddExpenseResource()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private var isOpen = false
    private var typeIncome = 1 // 1 donate, 2 receive , 3 loan, 4 borrow
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val onClickTypeExpense = object : OnActionData<AddExpenseViewModel> {
        override fun onAction(data: AddExpenseViewModel) {
            listData.forEach {
                if (it is AddExpenseViewModel) {
                    if (it.type == data.type) {
                        if (!it.isChoose) {
                            when (data.type) {
                                AddExpenseViewModel.Type.RECEIVE -> {
                                    replaceFragment(AddExpenseDonateFragment(false))
                                    view.tvToolbar.text = mResource.getTextReceive()
                                    typeIncome = 2
                                }
                                AddExpenseViewModel.Type.DONATE -> {
                                    replaceFragment(AddExpenseDonateFragment(true))
                                    view.tvToolbar.text = mResource.getTextDonate()
                                    typeIncome = 1
                                }
                                AddExpenseViewModel.Type.LOAN -> {
                                    replaceFragment(AddExpenseLoanFragment(true))
                                    view.tvToolbar.text = mResource.getTextLoan()
                                    typeIncome = 3
                                }
                                AddExpenseViewModel.Type.BORROW -> {
                                    replaceFragment(AddExpenseLoanFragment(false))
                                    view.tvToolbar.text = mResource.getTextBorrow()
                                    typeIncome = 4
                                }
                            }
                            it.isChoose = true
                        }
                    } else {
                        it.isChoose = false
                    }
                }
            }
            view.clBackground.gone()
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
        replaceFragment(AddExpenseDonateFragment(true))
        typeIncome = 1
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

    override fun handleAfterCreate() {
        showDialogNotify()
        view.imgNotify.visible()
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

    private fun showDialogNotify(title: String? = null) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
        }
        if (!title.isNullOrEmpty()) {
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    private fun replaceFragment(frm: Fragment) {
        mvpActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flChange, frm)
            .commit()
    }

    private fun initView() {
        view.cvType.gone()
        view.tvToolbar.text = mResource.getTextDonate()
        view.clWallet.setOnClickListener {
            isOpen = if (!isOpen) {
                view.cvType.visible()
                view.clBackground.visible()
                true
            } else {
                view.cvType.gone()
                view.clBackground.gone()
                false
            }
        }

        view.imgAdd.setOnClickListener {
            checkDataRequest()
        }

        view.clBackground.setOnClickListener {
            view.clBackground.gone()
            view.cvType.gone()
        }

        view.imgHistory.setOnClickListener {
            eventBusLifeCycle.sendData(TransactionDataBus())
        }
    }

    private fun checkDataRequest() {
        mvpActivity.hideKeyboard()
        if (AddExpenseFragment.model.totalMoney.getValueOrDefaultIsZero() == 0.0) {
            showDialogNotify(title = "Bạn chưa nhập giá trị tiêu thụ")
            return
        }

        if (typeIncome == 1 || typeIncome == 2) {

            if (AddExpenseFragment.model.category?.id == null) {
                showDialogNotify(title = "Bạn chưa chọn hạng mục tiêu thụ")
                return
            }
        }

        if (AddExpenseFragment.model.wallet == null) {
            showDialogNotify(title = "Bạn chưa chọn ví tài khoản cho chi tiêu này")
            return
        }

        if (typeIncome == 3 || typeIncome == 4) {
            if (AddExpenseFragment.model.loaner == null) {
                showDialogNotify(title = "Vui lòng điền đầy đủ thông tin")
                return
            }
        }

        if (AddExpenseFragment.model.date == null) {
            AddExpenseFragment.model.date = getCurrentDate()
        }

        if (AddExpenseFragment.model.time == null) {
            AddExpenseFragment.model.time = getCurrentTime()
        }

        if (AddExpenseFragment.model.title.isNullOrEmpty()) {
            AddExpenseFragment.model.title = "Không có mô tả cho chi tiêu này"
        }

        if(AddExpenseFragment.model.idBudget == null){
            val request = InOutComeRequest(
                loanIdLoan = if(AddExpenseFragment.model.loaner?.id != null) AddExpenseFragment.model.loaner?.id.toString() else null,
                amount = AddExpenseFragment.model.totalMoney.getValueOrDefaultIsZero(),
                categoryIdCate = AddExpenseFragment.model.category?.id.getValueOrDefaultIsZero().toString(),
                dateCome = AddExpenseFragment.model.date.getValueOrDefaultIsEmpty(),
                descriptionCome = AddExpenseFragment.model.title.getValueOrDefaultIsEmpty(),
                idType = typeIncome.getValueOrDefaultIsZero().toString(),
                tripIdTrip = if(AddExpenseFragment.model.trip?.id != null) AddExpenseFragment.model.trip?.id.toString() else null,
                walletIdWallet = AddExpenseFragment.model.wallet?.id.getValueOrDefaultIsZero().toString()
            )
            mPresenter.createExpense(request)
        }else{
            val request = InOutComeRequest(
                loanIdLoan = if(AddExpenseFragment.model.loaner?.id != null) AddExpenseFragment.model.loaner?.id.toString() else null,
                amount = AddExpenseFragment.model.totalMoney.getValueOrDefaultIsZero(),
                categoryIdCate = AddExpenseFragment.model.category?.id.getValueOrDefaultIsZero().toString(),
                dateCome = Utils.convertDateFormat(AddExpenseFragment.model.date.getValueOrDefaultIsEmpty(), SimpleDateFormat("dd/MM/yyyy"), SimpleDateFormat("yyyy-MM-dd")),
                descriptionCome = AddExpenseFragment.model.title.getValueOrDefaultIsEmpty(),
                idType = typeIncome.getValueOrDefaultIsZero().toString(),
                idBudget = AddExpenseFragment.model.idBudget.getValueOrDefaultIsZero().toString(),
                tripIdTrip = if(AddExpenseFragment.model.trip?.id != null) AddExpenseFragment.model.trip?.id.toString() else null,
                walletIdWallet = AddExpenseFragment.model.wallet?.id.getValueOrDefaultIsZero().toString()
            )
            mPresenter.createExpense(request)
        }
    }

    private fun getCurrentDate(): String {
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }

    private fun getCurrentTime(): String {
        val format = "hh:mm"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvTypeExpense, renderConfig)
        listViewMvp?.addViewRenderer(AddExpenseRenderer(mvpActivity, mResource, onClickTypeExpense))
        listViewMvp?.createView()
    }
}