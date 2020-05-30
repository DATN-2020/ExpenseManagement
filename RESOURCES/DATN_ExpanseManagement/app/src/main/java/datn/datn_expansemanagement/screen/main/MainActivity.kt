package datn.datn_expansemanagement.screen.main

import android.os.Bundle
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.main.presentation.MainView

class MainActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        return MainView(this, MainView.ViewCreator(this, null))
    }

}
