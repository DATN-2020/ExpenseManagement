package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseBudgetViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseBudgetViewRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseCategoryRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateInfoRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateTotalMoneyRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer.BudgetItemViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.presentation.PlanDetailResource
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.layout_add_expense_donate.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.util.*

class AddExpenseDonateView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val isDonate: Boolean = false
) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseDonateContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expense_donate, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mResource = AddExpenseDonateResource()
    private val mPresenter =
        AddExpenseDonatePresenter(
            screenNavigator = AndroidScreenNavigator((mvpActivity)),
            mvpActivity = mvpActivity
        )
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
    private val onClickExpand = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            data.isExpand = !data.isExpand
            listViewMvp?.notifyItemChanged(listData.indexOf(data))
        }
    }

    private val onChooseBudget = object : OnActionData<AddExpenseBudgetViewModel> {
        override fun onAction(data: AddExpenseBudgetViewModel) {
            if (AddExpenseFragment.model.wallet?.id != null) {
                mPresenter.getListBudget(AddExpenseFragment.model.wallet?.id!!)
                bottomSheet.show()
                bottomSheet.tvTitle.text = "Chọn ngân sách áp dụng"
            }else{
                showDialogNotify(title = "Bạn chưa chọn ví tiền cho giao dịch này")
            }

        }

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
        if(!title.isNullOrEmpty()){
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
            listViewMvp?.notifyDataChanged()
        }
    })

    private val onChooseCategory = object : OnActionData<AddExpenseCategoryViewModel> {
        override fun onAction(data: AddExpenseCategoryViewModel) {
            if (data.idCategory != null) {
                mPresenter.gotoCategoryActivity(data.idCategory)
            } else {
                mPresenter.gotoCategoryActivity()
            }
        }
    }

    private val onChooseWallet = object : OnActionData<AddExpenseCategoryViewModel> {
        override fun onAction(data: AddExpenseCategoryViewModel) {
            if (data.idWallet != null) {
                mPresenter.gotoChooseWalletActivity(data.idWallet)
            } else {
                mPresenter.gotoChooseWalletActivity()
            }
        }
    }

    private val onChooseDate = object : OnActionData<AddExpenseCategoryViewModel> {
        override fun onAction(data: AddExpenseCategoryViewModel) {
            val c: Calendar = Calendar.getInstance()
            val yyyy = c.get(Calendar.YEAR)
            val mm = c.get(Calendar.MONTH)
            val dd = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                mvpActivity,
                DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    val m = month + 1
                    data.date = "$day/$m/$year"
                    AddExpenseFragment.model.date = data.date
                    listViewMvp?.notifyDataChanged()
                },
                yyyy,
                mm,
                dd
            )
            datePickerDialog.show()
        }
    }

    private val onChooseTrip = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            mPresenter.gotoChooseTripActivity()
        }
    }

    private val onChooseLocation = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            mPresenter.gotoLocationActivity()
        }
    }

    private val onChooseFriend = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            mPresenter.gotoChooseFriend()
        }
    }

    private val onChooseTime = object : OnActionData<AddExpenseCategoryViewModel> {
        override fun onAction(data: AddExpenseCategoryViewModel) {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                mvpActivity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    if (minute < 10) {
                        data.time = "$hourOfDay: 0$minute"
                    } else {
                        data.time = "$hourOfDay: $minute"
                    }
                    AddExpenseFragment.model.time = data.time
                    listViewMvp?.notifyDataChanged()
                },
                hh,
                mm,
                true
            )
            timePickerDialog.show()
        }
    }

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
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
        mPresenter.getData(isDonate)
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

    override fun showBottomData(list: MutableList<ViewModel>) {
        listBottom.clear()
        if (list.isNotEmpty()) {
            listBottom.addAll(list)
        }
        listViewBottom?.setItems(listBottom)
        listViewBottom?.notifyDataChanged()
        bottomSheet.show()
    }

    private val mResourceProvider = PlanDetailResource()
    private val onItemBottomClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as BudgetItemViewModel
            listData.forEach {
                if (it is AddExpenseBudgetViewModel) {
                    it.id = dataItem.id
                    it.name = dataItem.name
                    AddExpenseFragment.model.idBudget = dataItem.id
                    listViewMvp?.notifyItemChanged(listData.indexOf(it))
                    bottomSheet.dismiss()
                }
            }
        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddExpanse, renderConfig)
        listViewMvp?.addViewRenderer(
            AddExpenseCategoryRenderer(
                mvpActivity,
                mResource,
                onChooseCategory,
                onChooseWallet,
                onChooseDate,
                onChooseTime
            )
        )
        listViewMvp?.addViewRenderer(
            AddExpenseDonateInfoRenderer(
                mvpActivity,
                mResource,
                onClickExpand,
                onChooseTrip,
                onChooseFriend,
                onChooseLocation
            )
        )
        listViewMvp?.addViewRenderer(AddExpenseBudgetViewRenderer(mvpActivity, onChooseBudget))
        listViewMvp?.addViewRenderer(AddExpenseDonateTotalMoneyRenderer(mvpActivity, mResource))
        listViewMvp?.createView()

        bottomSheet.setContentView(customView)
        bottomSheet.create()
        listViewBottom = ListViewMvp(mvpActivity, bottomSheet.rvChoose, renderConfigBottom)
        listViewBottom?.addViewRenderer(BudgetItemViewRenderer(mvpActivity, mResourceProvider))
        listViewBottom?.setOnItemRvClickedListener(onItemBottomClick)
        listViewBottom?.createView()
    }

}