package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseCategoryViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_choose_category.view.*
import java.util.*

class AddExpenseCategoryRenderer(
    context: Context,
    private val mResource: AddExpenseDonateResource,
    private val onChooseCategory: OnActionData<AddExpenseCategoryViewModel>,
    private val onChooseWallet: OnActionData<AddExpenseCategoryViewModel>,
    private val onChooseDate: OnActionData<AddExpenseCategoryViewModel>,
    private val onChooseTime: OnActionData<AddExpenseCategoryViewModel>
) :
    ViewRenderer<AddExpenseCategoryViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_choose_category
    }

    override fun getModelClass(): Class<AddExpenseCategoryViewModel> =
        AddExpenseCategoryViewModel::class.java

    override fun bindView(model: AddExpenseCategoryViewModel, viewRoot: View) {
        if (!model.nameCategory.isNullOrEmpty()) {
            viewRoot.tvChooseCategory.text = model.nameCategory
            viewRoot.tvChooseCategory.setTextColor(mResource.getColorCategory())
        } else {
            viewRoot.tvChooseCategory.text = mResource.getTextCategoryEmpty()
            viewRoot.tvChooseCategory.setTextColor(mResource.getColorEmpty())
        }

        if (!model.nameWallet.isNullOrEmpty()) {
            viewRoot.tvWallet.text = model.nameWallet
            viewRoot.tvWallet.setTextColor(mResource.getColorCategory())
        } else {
            viewRoot.tvWallet.text = mResource.getTextWalletEmpty()
            viewRoot.tvWallet.setTextColor(mResource.getColorCategory())
        }

        if (!model.time.isNullOrEmpty()) {
            viewRoot.tvTime.text = model.time
        } else {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            if(mm < 10){
                viewRoot.tvTime.text = "$hh: 0$mm"
            }else{
                viewRoot.tvTime.text = "$hh: $mm"
            }

        }

        if (!model.date.isNullOrEmpty()) {
            viewRoot.edtCalendar.text = model.date
        } else {
            val c: Calendar = Calendar.getInstance()
            val yyyy = c.get(Calendar.YEAR)
            val mm = c.get(Calendar.MONTH)
            val dd = c.get(Calendar.DAY_OF_MONTH)
            viewRoot.edtCalendar.text = "$dd/$mm/$yyyy"
        }
        viewRoot.tvChooseCategory.setOnClickListener {
            EventFireUtil.fireEvent(onChooseCategory, model)
        }

        viewRoot.tvWallet.setOnClickListener {
            EventFireUtil.fireEvent(onChooseWallet, model)
        }

        viewRoot.tvTime.setOnClickListener {
            EventFireUtil.fireEvent(onChooseTime, model)
        }

        viewRoot.edtCalendar.setOnClickListener {
            EventFireUtil.fireEvent(onChooseDate, model)
        }
    }

}