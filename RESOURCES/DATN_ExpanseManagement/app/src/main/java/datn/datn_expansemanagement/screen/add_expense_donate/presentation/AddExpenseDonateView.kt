package datn.datn_expansemanagement.screen.add_expense_donate.presentation

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
import datn.datn_expansemanagement.kotlinex.collection.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseCategoryRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateInfoRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer.AddExpenseDonateTotalMoneyRenderer
import datn.datn_expansemanagement.screen.main.data.EventBusCategory
import datn.datn_expansemanagement.screen.main.data.EventBusFriend
import datn.datn_expansemanagement.screen.main.data.EventBusTrip
import datn.datn_expansemanagement.screen.main.data.EventBusWallet
import kotlinx.android.synthetic.main.layout_add_expense_donate.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.util.*

class AddExpenseDonateView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AddExpenseDonateContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_expense_donate, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mResource = AddExpenseDonateResource()
    private val mPresenter =
        AddExpenseDonatePresenter(screenNavigator = AndroidScreenNavigator((mvpActivity)))
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val onClickExpand = object : OnActionData<AddExpenseDonateInfoViewModel> {
        override fun onAction(data: AddExpenseDonateInfoViewModel) {
            data.isExpand = !data.isExpand
            listViewMvp?.notifyDataChanged()
        }
    }

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
            when (data) {
                is EventBusCategory -> {
                    listData.forEach {
                        if (it is AddExpenseCategoryViewModel) {
                            it.idCategory =
                                AddExpenseFragment.model.category?.id.getValueOrDefaultIsZero()
                            it.nameCategory =
                                AddExpenseFragment.model.category?.name.getValueOrDefaultIsEmpty()
                        }
                    }
                }
                is EventBusWallet -> {
                    listData.forEach {
                        if (it is AddExpenseCategoryViewModel) {
                            it.idWallet =
                                AddExpenseFragment.model.wallet?.id.getValueOrDefaultIsZero()
                            it.nameWallet =
                                AddExpenseFragment.model.wallet?.name.getValueOrDefaultIsEmpty()
                        }
                    }
                }
                is EventBusTrip -> {
                    listData.forEach {
                        if (it is AddExpenseDonateInfoViewModel) {
                            it.idTrip = AddExpenseFragment.model.trip?.id.getValueOrDefaultIsZero()
                            it.tripName =
                                AddExpenseFragment.model.trip?.name.getValueOrDefaultIsEmpty()
                        }
                    }
                }
                is EventBusFriend -> {
//                    listData.forEach {
//                        if (it is AddExpenseDonateInfoViewModel) {
//                            val list = mutableListOf<AddExpenseDonateInfoViewModel.ItemFriend>()
//                            AddExpenseFragment.model.listFriend?.list?.forEach { data ->
//                                data as AddExpenseViewModel.Info.ListFriend.Friend
//                                list.add(AddExpenseDonateInfoViewModel.ItemFriend(
//                                    idFriend = data.id.getValueOrDefaultIsZero(),
//                                    friendName = data.name.getValueOrDefaultIsEmpty()
//                                ))
//                            }
//                            it.listFriend = list
//                        }
//                    }
                }
            }
            listViewMvp?.setItems(listData)
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
                    var m = month + 1
                    data.date = "$day/$m/$year"
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
        listViewMvp = ListViewMvp(mvpActivity, view.rvAddExpanseDonate, renderConfig)
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
                onChooseFriend
            )
        )
        listViewMvp?.addViewRenderer(AddExpenseDonateTotalMoneyRenderer(mvpActivity, mResource))
        listViewMvp?.createView()
    }

}