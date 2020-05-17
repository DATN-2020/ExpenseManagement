package datn.datn_expansemanagement.screen.add_expanse.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expanse.presentation.AddExpenseResource
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import kotlinx.android.synthetic.main.item_layout_add_expense.view.*

class AddExpenseRenderer(
    context: Context, private val mResource: AddExpenseResource,
    private val onClickTypeExpense: OnActionData<AddExpenseViewModel>
) : ViewRenderer<AddExpenseViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expense
    }

    override fun getModelClass(): Class<AddExpenseViewModel> = AddExpenseViewModel::class.java

    override fun bindView(model: AddExpenseViewModel, viewRoot: View) {
        when (model.type) {
            AddExpenseViewModel.Type.RECEIVE -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeExpense,
                    mResource.getTextReceive(),
                    R.drawable.ic_receive
                )
                viewRoot.tvTypeExpense.text = mResource.getTextReceive()
            }
            AddExpenseViewModel.Type.DONATE -> {
                GlideImageHelper(context).loadThumbnail(
                    viewRoot.imgTypeExpense,
                    mResource.getIconSubtract(),
                    R.drawable.ic_subtract
                )
                viewRoot.tvTypeExpense.text = mResource.getTextDonate()
            }
        }

        if (model.isChoose) {
            viewRoot.tvTypeExpense.setTextColor(mResource.getColorSelected())
            viewRoot.imgSelected.visible()
        } else {
            viewRoot.imgSelected.invisible()
            viewRoot.tvTypeExpense.setTextColor(mResource.getColorUnSelected())
        }

        viewRoot.clItemLayoutAddExpense.setOnClickListener {
            EventFireUtil.fireEvent(onClickTypeExpense, model)
        }
    }

}