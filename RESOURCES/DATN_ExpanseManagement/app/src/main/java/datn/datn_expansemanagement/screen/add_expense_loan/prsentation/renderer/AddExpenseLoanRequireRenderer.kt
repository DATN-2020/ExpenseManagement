package datn.datn_expansemanagement.screen.add_expense_loan.prsentation.renderer

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.AddExpenseLoanResource
import datn.datn_expansemanagement.screen.add_expense_loan.prsentation.model.AddExpenseLoanRequireViewModel
import kotlinx.android.synthetic.main.item_layout_loan_require.view.*
import java.util.*

class AddExpenseLoanRequireRenderer(
    context: Context,
    private val mResource: AddExpenseLoanResource,
    private val onChooseLoaner: OnActionData<AddExpenseLoanRequireViewModel>,
    private val onChooseWallet: OnActionData<AddExpenseLoanRequireViewModel>,
    private val onChooseDate: OnActionData<AddExpenseLoanRequireViewModel>,
    private val onChooseTime: OnActionData<AddExpenseLoanRequireViewModel>
) : ViewRenderer<AddExpenseLoanRequireViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_loan_require
    }

    override fun getModelClass(): Class<AddExpenseLoanRequireViewModel> =
        AddExpenseLoanRequireViewModel::class.java

    @SuppressLint("SetTextI18n")
    override fun bindView(model: AddExpenseLoanRequireViewModel, viewRoot: View) {
        if (!model.time.isNullOrEmpty()) {
            viewRoot.tvTime.text = model.time
        } else {
            val c: Calendar = Calendar.getInstance()
            val hh = c.get(Calendar.HOUR_OF_DAY)
            val mm = c.get(Calendar.MINUTE)
            if (mm < 10) {
                viewRoot.tvTime.text = "$hh: 0$mm"
            } else {
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

        if (AddExpenseFragment.model.wallet != null) {
            viewRoot.tvWallet.text =
                AddExpenseFragment.model.wallet?.name.getValueOrDefaultIsEmpty()

        } else {
            viewRoot.tvWallet.text = mResource.getTextWalletEmpty()
        }
        viewRoot.tvWallet.setTextColor(mResource.getColorCategory())

        if (model.idLoaner != null) {
            viewRoot.tvLoaner.text = model.nameLoaner.getValueOrDefaultIsEmpty()
        } else {
            viewRoot.tvLoaner.text = ""
            viewRoot.tvLoaner.hint = mResource.getEmptyLoaner()
        }

        if (model.isLoan) {
            viewRoot.tvLoan.text = mResource.getTextLoan()
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgLeft,
                R.drawable.ic_loan,
                R.drawable.ic_loan
            )
        } else {
            viewRoot.tvLoan.text = mResource.getTextBorrow()
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgLeft,
                R.drawable.ic_borrow,
                R.drawable.ic_borrow
            )
        }

        viewRoot.clLoaner.setOnClickListener {
            EventFireUtil.fireEvent(onChooseLoaner, model)
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