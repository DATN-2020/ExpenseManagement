package datn.datn_expansemanagement.screen.information.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.information.presentation.model.InfoItemViewModel
import kotlinx.android.synthetic.main.item_layout_information.view.*

class InfoItemViewRenderer (context: Context): ViewRenderer<InfoItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_information
    }

    override fun getModelClass(): Class<InfoItemViewModel> = InfoItemViewModel::class.java
    override fun bindView(model: InfoItemViewModel, viewRoot: View) {
        viewRoot.edtUser.setText(model.name)
        viewRoot.edtUser.isEnabled = false
    }

}