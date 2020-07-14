package datn.datn_expansemanagement.core.app.change_screen

import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent

interface ScreenNavigator {
    fun gotoCategoryActivity(categoryId: Int? = null, isPlan: Boolean = false)
    fun gotoListTypeCategory(data: TypeCategoryDataIntent? = null)
    fun gotoAddCategoryActivity()
    fun gotoChooseWalletActivity(walletId: Int? = null)
    fun gotoChooseTripActivity()
    fun gotoChooseFriendActivity()
    fun gotoLocationActivity()
    fun gotoHistoryActivity()
    fun gotoExchangeRateActivity()
    fun gotoLoginActivity(isLogin: Boolean, user: PassportDataIntent? = null)
    fun gotoReportDetailActivity(data: ReportViewModel)
    fun gotoMainActivity()
    fun gotoPlanDetailActivity(planType: PlanItemViewModel)
    fun gotoAddWalletActivity(type: Int)
    fun gotoControlWalletActivity(data : WalletViewModel, isOtherWallet: Boolean)
    fun gotoAddPlanActivity(typeAdd: PlanItemViewModel)
}