package datn.datn_expansemanagement.screen.add_expense_loan.prsentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.ViewGroup
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
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateInfoRenderer
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.model.AddExpenseLoanRequireViewModel
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.renderer.AddExpenseLoanRequireRenderer
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.renderer.AddExpenseLoanTotalMoneyRenderer
import kotlinx.android.synthetic.main.layout_add_expense_donate.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.util.*

class AddExpenseLoanView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val isLoan: Boolean = false
) : AndroidMvpView(mvpActivity, viewCreator), AddExpenseLoanContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expense_donate, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val mPresenter =
        AddExpenseLoanPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    private val mResourceProvider = AddExpenseDonateResource()
    private val mResource = AddExpenseLoanResource()

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
            if(!AddExpenseFragment.listFriend.list.isNullOrEmpty()){
                val item = AddExpenseFragment.listFriend.list[0] as AddExpenseViewModel.Info.ListFriend.Friend
                listData.forEach {
                    if(it is AddExpenseLoanRequireViewModel){
                        it.nameLoaner = item.name.getValueOrDefaultIsEmpty()
                    }
                }
            }
            listViewMvp?.notifyDataChanged()
        }
    })

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
        mPresenter.getData(isLoan)
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
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddExpanse, renderConfig)
        listViewMvp?.addViewRenderer(
            AddExpenseLoanTotalMoneyRenderer(
                mvpActivity,
                mResourceProvider
            )
        )
        listViewMvp?.addViewRenderer(
            AddExpenseLoanRequireRenderer(
                mvpActivity,
                mResource,
                onChooseLoaner,
                onChooseWallet,
                onChooseDate,
                onChooseTime
            )
        )
        listViewMvp?.addViewRenderer(
            AddExpenseDonateInfoRenderer(
                mvpActivity,
                mResourceProvider,
                onClickExpand,
                onChooseTrip,
                onChooseFriend
            )
        )
        listViewMvp?.createView()
    }

    private val onClickExpand = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            data.isExpand = !data.isExpand
            listViewMvp?.notifyDataChanged()
        }
    }

    private val onChooseTrip = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            mPresenter.gotoChooseTripActivity()
        }
    }

    private val onChooseFriend = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            mPresenter.gotoChooseFriend()
        }
    }
    private val onChooseDate = object : OnActionData<AddExpenseLoanRequireViewModel> {
        override fun onAction(data: AddExpenseLoanRequireViewModel) {
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

    private val onChooseTime = object : OnActionData<AddExpenseLoanRequireViewModel> {
        override fun onAction(data: AddExpenseLoanRequireViewModel) {
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

    private val onChooseWallet = object : OnActionData<AddExpenseLoanRequireViewModel> {
        override fun onAction(data: AddExpenseLoanRequireViewModel) {
            if (data.idWallet != null) {
                mPresenter.gotoChooseWalletActivity(data.idWallet)
            } else {
                mPresenter.gotoChooseWalletActivity()
            }
        }
    }

    private val onChooseLoaner = object : OnActionData<AddExpenseLoanRequireViewModel>{
        override fun onAction(data: AddExpenseLoanRequireViewModel) {
            mPresenter.gotoChooseFriend()
        }

    }
}