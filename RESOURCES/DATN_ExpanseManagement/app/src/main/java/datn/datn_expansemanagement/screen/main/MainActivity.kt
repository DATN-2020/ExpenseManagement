package datn.datn_expansemanagement.screen.main

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.main.presentation.MainView

class MainActivity : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return MainView(this, MainView.ViewCreator(this, null))
    }
}
