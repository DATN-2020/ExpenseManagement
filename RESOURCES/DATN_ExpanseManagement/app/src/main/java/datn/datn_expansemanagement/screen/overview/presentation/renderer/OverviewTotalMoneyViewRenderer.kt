package datn.datn_expansemanagement.screen.overview.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.model.OverviewTotalMoneyViewModel
import kotlinx.android.synthetic.main.item_layout_overview_total_money.view.*

class OverviewTotalMoneyViewRenderer (context: Context,
private val onActionNotify: OnActionNotify): ViewRenderer<OverviewTotalMoneyViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_overview_total_money
    }

    override fun getModelClass(): Class<OverviewTotalMoneyViewModel> = OverviewTotalMoneyViewModel::class.java

    override fun bindView(model: OverviewTotalMoneyViewModel, viewRoot: View) {
        viewRoot.tvTotalMoney.text = Utils.formatMoneyVND(model.total)
        viewRoot.llTotalMoney.setOnClickListener {
            EventFireUtil.fireEvent(onActionNotify)
        }
    }

}