package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.presentation

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BudgetItemViewModel
import kotlinx.android.synthetic.main.item_layout_plan_detail_budget.view.*

class BudgetItemViewRenderer (context: Context): ViewRenderer<BudgetItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_plan_detail_budget
    }

    override fun getModelClass(): Class<BudgetItemViewModel> = BudgetItemViewModel::class.java

    override fun bindView(model: BudgetItemViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        viewRoot.tvAccumulation.text = model.totalPrice.toString()
        viewRoot.tvRest.text = model.currentPrice.toString()
    }

}