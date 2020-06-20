package datn.datn_expansemanagement.screen.login.item_register

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.login.item_register.presentation.ItemRegisterView

class ItemRegisterFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ItemRegisterView(getMvpActivity(), ItemRegisterView.ViewCreator(getMvpActivity(), null))
    }

}