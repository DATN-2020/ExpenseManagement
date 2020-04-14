package datn.datn_expansemanagement.screen.test.presentation

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment

class TestFragment : MvpFragment(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return TestView(getMvpActivity(), TestView.ViewCreator(getMvpActivity(), null))
    }

}