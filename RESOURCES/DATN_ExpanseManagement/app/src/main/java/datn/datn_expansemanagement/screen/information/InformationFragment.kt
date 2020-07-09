package datn.datn_expansemanagement.screen.information

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.information.presentation.InformationView

class InformationFragment: MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return InformationView(getMvpActivity(), InformationView.ViewCreator(getMvpActivity(), null))
    }

}