package datn.datn_expansemanagement.screen.history

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.history.presentation.HistoryView

class HistoryActivity : MvpActivity(){
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        return HistoryView(this, HistoryView.ViewCreator(this, null))
    }
}