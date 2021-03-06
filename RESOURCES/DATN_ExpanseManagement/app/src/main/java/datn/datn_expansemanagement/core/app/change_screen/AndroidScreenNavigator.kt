package datn.datn_expansemanagement.core.app.change_screen

import android.content.Intent
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.MapsActivity
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.add_category.AddCategoryActivity
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.add_plan.AddPlanActivity
import datn.datn_expansemanagement.screen.add_wallet.AddWalletActivity
import datn.datn_expansemanagement.screen.category.CategoryActivity
import datn.datn_expansemanagement.screen.contacts.ContactsActivity
import datn.datn_expansemanagement.screen.control_saving.ControlSavingActivity
import datn.datn_expansemanagement.screen.control_wallet.ControlWalletActivity
import datn.datn_expansemanagement.screen.exchange_rate.ExchangeRateActivity
import datn.datn_expansemanagement.screen.history.HistoryActivity
import datn.datn_expansemanagement.screen.list_type_category.ListTypeCategoryActivity
import datn.datn_expansemanagement.screen.list_wallet.ListWalletActivity
import datn.datn_expansemanagement.screen.login.LoginActivity
import datn.datn_expansemanagement.screen.main.MainActivity
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.PlanDetailActivity
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report_detail.main.ReportDetailActivity
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent
import datn.datn_expansemanagement.screen.trip.TripActivity

class AndroidScreenNavigator constructor(private val mvpActivity: MvpActivity) : ScreenNavigator {
    override fun gotoCategoryActivity(
        categoryId: Int?,
        isPlan: Boolean,
        isDonate: Boolean
    ) {
        val intent = Intent(mvpActivity, CategoryActivity::class.java)
        intent.putExtra("idCategory", categoryId)
        intent.putExtra("isPlan", isPlan)
        intent.putExtra("isDonate", isDonate)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_CATEGORY)
    }

    override fun gotoAddCategoryActivity() {
        val intent = Intent(mvpActivity, AddCategoryActivity::class.java)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_ADD_CATEGORY)
    }

    override fun gotoListTypeCategory(data: TypeCategoryDataIntent?) {
        val intent = Intent(mvpActivity, ListTypeCategoryActivity::class.java)
        intent.putExtra(TypeCategoryDataIntent::class.java.simpleName, data)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_TYPE_CATEGORY)
    }

    override fun gotoChooseWalletActivity(walletId: Int?) {
        val intent = Intent(mvpActivity, ListWalletActivity::class.java)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_WALLET)
    }

    override fun gotoChooseTripActivity() {
        val intent = Intent(mvpActivity, TripActivity::class.java)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_TRIP)
    }

    override fun gotoChooseFriendActivity() {
        val intent = Intent(mvpActivity, ContactsActivity::class.java)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_FRIEND)
    }

    override fun gotoLocationActivity() {
        val intent = Intent(mvpActivity, MapsActivity::class.java)
        mvpActivity.startActivityForResult(intent, Request.REQUEST_CODE_LOCATION)
    }

    override fun gotoHistoryActivity() {
        val intent = Intent(mvpActivity, HistoryActivity::class.java)
        mvpActivity.startActivity(intent)
    }

    override fun gotoExchangeRateActivity() {
        val intent = Intent(mvpActivity, ExchangeRateActivity::class.java)
        mvpActivity.startActivity(intent)
    }

    override fun gotoReportDetailActivity(data: ReportViewModel) {
        val intent = Intent(mvpActivity, ReportDetailActivity::class.java)
        intent.putExtra(ReportViewModel::class.java.simpleName, data)
        mvpActivity.startActivity(intent)
    }

    override fun gotoLoginActivity(isLogin: Boolean, user: PassportDataIntent?) {
        val intent = Intent(mvpActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("isLogin", isLogin)
        intent.putExtra(PassportDataIntent::class.java.simpleName, user)
        mvpActivity.startActivity(intent)
    }

    override fun gotoMainActivity() {
        val intent = Intent(mvpActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        mvpActivity.startActivity(intent)
    }

    override fun gotoAddWalletActivity(type: Int) {
        val intent = Intent(mvpActivity, AddWalletActivity::class.java)
        intent.putExtra("typeWallet", type)
        mvpActivity.startActivity(intent)
    }

    override fun gotoPlanDetailActivity(planType: PlanItemViewModel) {
        val intent = Intent(mvpActivity, PlanDetailActivity::class.java)
        intent.putExtra(PlanItemViewModel::class.java.simpleName, planType)
        mvpActivity.startActivity(intent)
    }

    override fun gotoControlWalletActivity(data: WalletViewModel, isOtherWallet: Boolean) {
        val intent = Intent(mvpActivity, ControlWalletActivity::class.java)
        intent.putExtra(WalletViewModel::class.java.simpleName, data)
        intent.putExtra("isOtherWallet", isOtherWallet)
        mvpActivity.startActivityForResult(intent,  Request.REQUEST_CODE_CONTROL_WALLET)
    }

    override fun gotoAddPlanActivity(typeAdd: PlanItemViewModel) {
        val intent = Intent(mvpActivity, AddPlanActivity::class.java)
        intent.putExtra(PlanItemViewModel::class.java.simpleName, typeAdd)
        mvpActivity.startActivity(intent)
    }

    override fun gotoControlSavingActivity(
        isCome: Boolean,
        data: ItemAccountAccumulationViewModel
    ) {
        val intent = Intent(mvpActivity, ControlSavingActivity::class.java)
        intent.putExtra(ItemAccountAccumulationViewModel::class.java.simpleName, data)
        intent.putExtra("isCome", isCome)
        mvpActivity.startActivity(intent)
    }
}