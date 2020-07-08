package datn.datn_expansemanagement.screen.add_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_plan.presentation.model.TimeItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_plan_choose_time.view.*

class TimeItemViewRenderer (context: Context): ViewRenderer<TimeItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_plan_choose_time
    }

    override fun getModelClass(): Class<TimeItemViewModel> = TimeItemViewModel::class.java

    override fun bindView(model: TimeItemViewModel, viewRoot: View) {
        viewRoot.tvTitleTime.text = model.name
        viewRoot.tvValueTime.text = model.value

        if(model.isChoose){
            viewRoot.imgChecked.visible()
        }else{
            viewRoot.imgChecked.gone()
        }

        if(model.isLast){
            viewRoot.viewBottom.invisible()
        }else{
            viewRoot.viewBottom.visible()
        }
    }

}