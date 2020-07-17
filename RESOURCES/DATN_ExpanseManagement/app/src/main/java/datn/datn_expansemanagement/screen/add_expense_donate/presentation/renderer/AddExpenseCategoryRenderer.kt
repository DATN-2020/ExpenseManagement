package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
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
        if (AddExpenseFragment.model.category != null) {
            viewRoot.tvChooseCategory.text = AddExpenseFragment.model.category?.name.getValueOrDefaultIsEmpty()
            viewRoot.tvChooseCategory.setTextColor(mResource.getColorCategory())
        } else {
            viewRoot.tvChooseCategory.text = mResource.getTextCategoryEmpty()
            viewRoot.tvChooseCategory.setTextColor(mResource.getColorEmpty())
        }

        if (AddExpenseFragment.model.wallet != null) {
            viewRoot.tvWallet.text = AddExpenseFragment.model.wallet?.name.getValueOrDefaultIsEmpty()
            viewRoot.tvWallet.setTextColor(mResource.getColorCategory())
        } else {
            viewRoot.tvWallet.text = mResource.getTextWalletEmpty()
            viewRoot.tvWallet.setTextColor(mResource.getColorCategory())
        }

        viewRoot.edtComment.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                AddExpenseFragment.model.title = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

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
            val mm = c.get(Calendar.MONTH) + 1
            val dd = c.get(Calendar.DAY_OF_MONTH)
            viewRoot.edtCalendar.text = "$dd/$mm/$yyyy"
        }
        viewRoot.clCategory.setOnClickListener {
            EventFireUtil.fireEvent(onChooseCategory, model)
        }

        viewRoot.clWallet.setOnClickListener {
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