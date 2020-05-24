package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_choose_category.view.*

class AddExpenseCategoryRenderer(
    context: Context,
    private val mResource: AddExpenseDonateResource,
    private val onChooseCategory: OnActionData<AddExpenseCategoryViewModel>,
    private val onChooseWallet: OnActionData<AddExpenseCategoryViewModel>
) :
    ViewRenderer<AddExpenseCategoryViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_choose_category
    }

    override fun getModelClass(): Class<AddExpenseCategoryViewModel> =
        AddExpenseCategoryViewModel::class.java

    override fun bindView(model: AddExpenseCategoryViewModel, viewRoot: View) {
        if(!model.nameCategory.isNullOrEmpty()){
            viewRoot.tvChooseCategory.text = model.nameCategory
            viewRoot.tvChooseCategory.setTextColor(mResource.getColorCategory())
        }else{
            viewRoot.tvChooseCategory.text = mResource.getTextCategoryEmpty()
            viewRoot.tvChooseCategory.setTextColor(mResource.getColorEmpty())
        }

        if(!model.nameWallet.isNullOrEmpty()){
            viewRoot.tvWallet.text = model.nameWallet
            viewRoot.tvWallet.setTextColor(mResource.getColorCategory())
        }else{
            viewRoot.tvWallet.text = mResource.getTextWalletEmpty()
            viewRoot.tvWallet.setTextColor(mResource.getColorCategory())
        }
        viewRoot.tvChooseCategory.setOnClickListener {
            EventFireUtil.fireEvent(onChooseCategory, model)
        }

        viewRoot.tvWallet.setOnClickListener {
            EventFireUtil.fireEvent(onChooseWallet, model)
        }
    }

}