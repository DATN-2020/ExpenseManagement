package datn.datn_expansemanagement.screen.login.item_login

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.login.item_login.presentation.ItemLoginView

class ItemLoginFragment(private val userName: String? = null) : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ItemLoginView(getMvpActivity(), ItemLoginView.ViewCreator(getMvpActivity(), null), userName)
    }

}