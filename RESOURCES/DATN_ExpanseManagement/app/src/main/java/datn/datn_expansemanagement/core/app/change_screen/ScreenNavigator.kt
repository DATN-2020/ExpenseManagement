package datn.datn_expansemanagement.core.app.change_screen

import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent

interface ScreenNavigator {
    fun gotoCategoryActivity(categoryId: Int? = null)
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
    fun gotoAddWalletActivity(type: Int)
}