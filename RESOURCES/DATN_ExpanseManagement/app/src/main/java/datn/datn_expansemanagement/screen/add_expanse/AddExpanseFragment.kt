package datn.datn_expansemanagement.screen.add_expanse

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.AddExpanseView

class AddExpanseFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return AddExpanseView(getMvpActivity(), AddExpanseView.ViewCreator(getMvpActivity(), null))
    }
}