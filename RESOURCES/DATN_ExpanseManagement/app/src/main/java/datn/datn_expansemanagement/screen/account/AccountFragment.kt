package datn.datn_expansemanagement.screen.account

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.account.presentation.AccountView

class AccountFragment:MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return AccountView(getMvpActivity(),AccountView.ViewCreator(getMvpActivity(),null))
    }
}