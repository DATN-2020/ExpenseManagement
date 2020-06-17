package datn.datn_expansemanagement.screen.overview.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.model.OverviewHistoryCurrentlyViewModel
import kotlinx.android.synthetic.main.item_layout_overview_history_recently.view.*

class OverviewHistoryCurrentlyViewRenderer(
    context: Context,
    private val onActionNotify: OnActionNotify
) : ViewRenderer<OverviewHistoryCurrentlyViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_overview_history_recently
    }

    override fun getModelClass(): Class<OverviewHistoryCurrentlyViewModel> =
        OverviewHistoryCurrentlyViewModel::class.java

    override fun bindView(model: OverviewHistoryCurrentlyViewModel, viewRoot: View) {
        viewRoot.tvMore.setOnClickListener {
            EventFireUtil.fireEvent(onActionNotify)
        }
    }

}