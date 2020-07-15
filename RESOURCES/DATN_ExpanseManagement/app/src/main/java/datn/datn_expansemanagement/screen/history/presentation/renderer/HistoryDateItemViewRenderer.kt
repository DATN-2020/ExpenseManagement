package datn.datn_expansemanagement.screen.history.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.history.presentation.model.HistoryDateItemViewModel
import kotlinx.android.synthetic.main.item_history_date.view.*

class HistoryDateItemViewRenderer (context: Context): ViewRenderer<HistoryDateItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_history_date
    }

    override fun getModelClass(): Class<HistoryDateItemViewModel> = HistoryDateItemViewModel::class.java

    override fun bindView(model: HistoryDateItemViewModel, viewRoot: View) {
        viewRoot.tvNumberDay.text = model.numberDay.toString()
        viewRoot.tvDay.text = model.dayOfWeek
        viewRoot.tvMonth.text = model.month
        viewRoot.tvTotalMoneyIncome.text = Utils.formatMoney(model.inCome.getValueOrDefaultIsZero())
        viewRoot.tvTotalMoneyOutcome.text = Utils.formatMoney(model.outCome.getValueOrDefaultIsZero())
    }

}