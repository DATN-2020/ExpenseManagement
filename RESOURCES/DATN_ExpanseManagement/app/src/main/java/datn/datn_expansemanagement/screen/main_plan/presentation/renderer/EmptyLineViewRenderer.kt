package datn.datn_expansemanagement.screen.main_plan.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.main_plan.presentation.model.EmptyLineViewModel

class EmptyLineViewRenderer (context: Context): ViewRenderer<EmptyLineViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.empty_line
    }

    override fun getModelClass(): Class<EmptyLineViewModel> = EmptyLineViewModel::class.java
    override fun bindView(model: EmptyLineViewModel, viewRoot: View) {
    }

}