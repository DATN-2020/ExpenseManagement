package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.NoDataItemViewModel

class NoDataViewRenderer (context: Context): ViewRenderer<NoDataItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_no_data_vholder
    }

    override fun getModelClass(): Class<NoDataItemViewModel> = NoDataItemViewModel::class.java

    override fun bindView(model: NoDataItemViewModel, viewRoot: View) {
    }

}