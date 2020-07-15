package datn.datn_expansemanagement.screen.main.presentation

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.Request
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.screen.account.AccountFragment
import datn.datn_expansemanagement.screen.account.item_account.data.OnReportWalletDataBus
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.data.TransactionDataBus
import datn.datn_expansemanagement.screen.information.InformationFragment
import datn.datn_expansemanagement.screen.main.data.*
import datn.datn_expansemanagement.screen.main_plan.OverviewFragment
import datn.datn_expansemanagement.screen.report.ReportFragment
import kotlinx.android.synthetic.main.activity_main.view.*

class MainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), MainContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.activity_main, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mResource = MainResource()
    private val mPresenter = MainPresenter()

    private var overviewFragment: OverviewFragment? = null
    private var accountFragment: AccountFragment? = null
    private var reportFragment: ReportFragment? = null
    private var addExpenseFragment: AddExpenseFragment? = null
    private var infoFragment: InformationFragment? = null
    private var idWallet: Int? = null
    private var isCard: Boolean = false

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
            when(data){
                is TransactionDataBus->{
                    view.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
                        showFragmentForMenuItem(menuItem.itemId)
                        return@setOnNavigationItemSelectedListener true
                    }
                    view.bottomNavigation.selectedItemId = R.id.menuReport
                }

                is OnReportWalletDataBus->{
                    view.bottomNavigation.selectedItemId = R.id.menuReport
                    idWallet = data.idWallet
                    isCard = data.isCard
                    showFragmentForMenuItem(view.bottomNavigation.selectedItemId )

                }
            }
        }
    })

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        view.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            showFragmentForMenuItem(menuItem.itemId)
            return@setOnNavigationItemSelectedListener true
        }
        view.bottomNavigation.selectedItemId = R.id.menuReport
        mvpActivity.setFullScreen()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        if(isCategory){
            eventBusLifeCycle.sendData(EventBusCategory())
        }

        if(isWallet){
            eventBusLifeCycle.sendData(EventBusWallet())
        }

        if(isTrip){
            eventBusLifeCycle.sendData(EventBusTrip())
        }
        if(isFriend){
            eventBusLifeCycle.sendData(EventBusFriend())
        }
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    private var isCategory = false
    private var isWallet = false
    private var isTrip = false
    private var isFriend = false

    override fun onViewResult(viewResult: ViewResult) {
        super.onViewResult(viewResult)
        when (viewResult.requestCode) {
            Request.REQUEST_CODE_CATEGORY -> {
                isCategory = true
            }
            Request.REQUEST_CODE_WALLET -> {
                isWallet = true
            }
            Request.REQUEST_CODE_TRIP -> {
                isTrip = true
            }
            Request.REQUEST_CODE_FRIEND -> {
                isFriend = true
            }
        }
    }

    private fun showFragmentForMenuItem(itemId: Int) {
        try {
            val ft = mvpActivity.supportFragmentManager.beginTransaction()
            checkFragmentExist()
            when (itemId) {
                R.id.menuOverview -> if (overviewFragment != null && overviewFragment?.isAdded!!) {
                    ft.show(overviewFragment!!)
                } else {
                    overviewFragment = OverviewFragment()
                    ft.replace(R.id.mainFrameLayout, overviewFragment!!, itemId.toString())
                }

                R.id.menuAccount -> if (accountFragment != null && accountFragment?.isAdded!!) {
                    ft.show(accountFragment!!)
                } else {
                    accountFragment = AccountFragment()
                    ft.replace(R.id.mainFrameLayout, accountFragment!!, itemId.toString())
                }
                R.id.menuReport -> if (reportFragment != null && reportFragment!!.isAdded) {
                    ft.show(reportFragment!!)
                } else {
                    reportFragment = ReportFragment(idWallet, isCard)
                    ft.replace(R.id.mainFrameLayout, reportFragment!!, itemId.toString())
                }
                R.id.menuAddExpense -> if (addExpenseFragment != null && addExpenseFragment!!.isAdded) {
                    ft.show(addExpenseFragment!!)
                }
                else {
                    addExpenseFragment = AddExpenseFragment()
                    ft.replace(R.id.mainFrameLayout, addExpenseFragment!!, itemId.toString())
                }

                R.id.menuInfo -> if (infoFragment != null && infoFragment!!.isAdded) {
                    ft.show(infoFragment!!)
                }
                else {
                    infoFragment = InformationFragment()
                    ft.replace(R.id.mainFrameLayout, infoFragment!!, itemId.toString())
                }
            }
            hideOtherFragment(ft, itemId)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun checkFragmentExist() {
        val fragments = mvpActivity.supportFragmentManager.fragments
        for (f in fragments) {
            if (overviewFragment == null && f is OverviewFragment) {
                overviewFragment = f
            }
            if (accountFragment == null && f is AccountFragment) {
                accountFragment = f
            }
            if (reportFragment == null && f is ReportFragment) {
                reportFragment = f
            }
            if (addExpenseFragment == null && f is AddExpenseFragment) {
                addExpenseFragment = f
            }
            if (infoFragment == null && f is InformationFragment) {
                infoFragment = f
            }
        }
    }

    private fun hideOtherFragment(ft: FragmentTransaction, itemId: Int) {
        if (overviewFragment != null && overviewFragment!!.isAdded && itemId != R.id.menuOverview)
            ft.hide(overviewFragment!!)
        if (accountFragment != null && accountFragment?.isAdded!! && itemId != R.id.menuAccount)
            ft.hide(accountFragment!!)
        if (reportFragment != null && reportFragment!!.isAdded && itemId != R.id.menuReport)
            ft.hide(reportFragment!!)
        if (addExpenseFragment != null && addExpenseFragment!!.isAdded && itemId != R.id.menuAddExpense)
            ft.hide(addExpenseFragment!!)
        if (infoFragment != null && infoFragment!!.isAdded && itemId != R.id.menuInfo)
            ft.hide(infoFragment!!)
    }
}