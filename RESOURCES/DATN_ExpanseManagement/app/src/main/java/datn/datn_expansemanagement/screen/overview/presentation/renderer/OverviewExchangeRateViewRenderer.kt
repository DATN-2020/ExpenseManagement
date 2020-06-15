package datn.datn_expansemanagement.screen.overview.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.model.OverviewExchangeRateViewModel
import kotlinx.android.synthetic.main.item_layout_overview_exchange_rate.view.*

class OverviewExchangeRateViewRenderer (context: Context,
private val onActionNotify: OnActionNotify): ViewRenderer<OverviewExchangeRateViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_overview_exchange_rate
    }

    override fun getModelClass(): Class<OverviewExchangeRateViewModel> = OverviewExchangeRateViewModel::class.java

    override fun bindView(model: OverviewExchangeRateViewModel, viewRoot: View) {
        viewRoot.tvMore.setOnClickListener {
            EventFireUtil.fireEvent(onActionNotify)
        }
    }

}