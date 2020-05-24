package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_info.view.*

class AddExpenseDonateInfoRenderer(
    context: Context, private val mResource: AddExpenseDonateResource,
    private val onClickExpand: OnActionData<AddExpenseDonateInfoViewModel>,
    private val onChooseTrip: OnActionData<AddExpenseDonateInfoViewModel>
) : ViewRenderer<AddExpenseDonateInfoViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_info
    }

    override fun getModelClass(): Class<AddExpenseDonateInfoViewModel> =
        AddExpenseDonateInfoViewModel::class.java

    override fun bindView(model: AddExpenseDonateInfoViewModel, viewRoot: View) {
        if (model.isExpand) {
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
            EventFireUtil.fireEvent(onClickExpand, model)
        }

        viewRoot.edtTrip.setOnClickListener {
            EventFireUtil.fireEvent(onChooseTrip, model)
        }
    }

}