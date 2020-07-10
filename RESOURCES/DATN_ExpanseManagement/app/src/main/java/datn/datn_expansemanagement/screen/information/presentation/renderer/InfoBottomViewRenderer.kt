package datn.datn_expansemanagement.screen.information.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.information.presentation.model.InfoBottomViewModel
import kotlinx.android.synthetic.main.item_layout_information_bottom.view.*

class InfoBottomViewRenderer(
    context: Context,
    private val onActionNotify: OnActionNotify
) : ViewRenderer<InfoBottomViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_information_bottom
    }

    override fun getModelClass(): Class<InfoBottomViewModel> = InfoBottomViewModel::class.java
    override fun bindView(model: InfoBottomViewModel, viewRoot: View) {
        viewRoot.btnViewReport.setOnClickListener {
            EventFireUtil.fireEvent(onActionNotify)
        }
    }

}