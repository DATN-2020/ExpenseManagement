package datn.datn_expansemanagement.screen.history.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.history.presentation.model.HistoryTotalItemViewModel
import kotlinx.android.synthetic.main.item_total_income_outcome.view.*

class HistoryTotalItemViewRenderer (context: Context): ViewRenderer<HistoryTotalItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_total_income_outcome
    }

    override fun getModelClass(): Class<HistoryTotalItemViewModel> = HistoryTotalItemViewModel::class.java

    override fun bindView(model: HistoryTotalItemViewModel, viewRoot: View) {
        viewRoot.tvMoneyIncome.text = Utils.formatMoneyVND(model.totalIncome)
        viewRoot.tvMoneyOutcome.text = Utils.formatMoneyVND(model.totalOutCome)
    }

}