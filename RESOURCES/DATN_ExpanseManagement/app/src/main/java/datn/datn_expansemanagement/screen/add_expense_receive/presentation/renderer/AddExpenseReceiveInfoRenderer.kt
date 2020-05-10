package datn.datn_expansemanagement.screen.add_expense_receive.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.model.AddExpenseReceiveInfoViewModel
import datn.datn_expansemanagement.screen.add_expense_receive.presentation.AddExpenseReceiveResource
import kotlinx.android.synthetic.main.item_layout_add_expanse_info.view.*

class AddExpenseReceiveInfoRenderer(
    context: Context, private val mResource: AddExpenseReceiveResource,
    private val onClickExpand: OnActionData<AddExpenseReceiveInfoViewModel>
) : ViewRenderer<AddExpenseReceiveInfoViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_info
    }

    override fun getModelClass(): Class<AddExpenseReceiveInfoViewModel> =
        AddExpenseReceiveInfoViewModel::class.java

    override fun bindView(modelReceive: AddExpenseReceiveInfoViewModel, viewRoot: View) {
        if (modelReceive.isExpand) {
            viewRoot.clTop.visible()
            viewRoot.viewBottom3.visible()
            viewRoot.tvExpand.text = mResource.getTextCollapse()
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgExpand,
                mResource.getIconCollapse(),
                R.drawable.ic_keyboard_arrow_up_blue_24dp
            )
        } else {
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgExpand,
                mResource.getIconExpand(),
                R.drawable.ic_keyboard_arrow_down_blue_24dp
            )
            viewRoot.viewBottom3.gone()
            viewRoot.clTop.gone()
            viewRoot.tvExpand.text = mResource.getTextExpand()
        }

        viewRoot.tvExpand.setOnClickListener {
            EventFireUtil.fireEvent(onClickExpand, modelReceive)
        }
    }

}