package datn.datn_expansemanagement.screen.list_wallet

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import datn.datn_expansemanagement.screen.list_wallet.presentation.ListWalletView

class ListWalletActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        return ListWalletView(this, ListWalletView.ViewCreator(this , null))
    }
}
