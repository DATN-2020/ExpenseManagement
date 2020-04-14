package datn.datn_expansemanagement.screen.test

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.test.presentation.TestView

class TestActivity : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return TestView(this, TestView.ViewCreator(this, null))
    }
}