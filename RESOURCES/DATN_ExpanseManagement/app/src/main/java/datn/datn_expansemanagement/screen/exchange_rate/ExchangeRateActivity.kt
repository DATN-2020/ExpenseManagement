package datn.datn_expansemanagement.screen.exchange_rate

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.exchange_rate.presentation.ExchangeRateView

class ExchangeRateActivity : MvpActivity() {
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        return ExchangeRateView(this, ExchangeRateView.ViewCreator(this, null))
    }

}