package datn.datn_expansemanagement.screen.main_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanDesItemViewModel
import kotlinx.android.synthetic.main.item_des_layout_plan.view.*

class PlanDesItemViewRenderer (context: Context): ViewRenderer<PlanDesItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_des_layout_plan
    }

    override fun getModelClass(): Class<PlanDesItemViewModel> = PlanDesItemViewModel::class.java

    override fun bindView(model: PlanDesItemViewModel, viewRoot: View) {
        viewRoot.tvDes.text = model.des
    }

}