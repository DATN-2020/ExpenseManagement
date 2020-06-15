package datn.datn_expansemanagement.screen.add_expense_loan.prsentation

import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AddExpenseLoanResource: AndroidResourceProvider(){
    fun getColorCategory(): Int{
        return resourceManager.getColor(R.color.black)
    }

    fun getColorEmpty(): Int{
        return resourceManager.getColor(R.color.color_bdbec1)
    }

    fun getTextWalletEmpty(): String{
        return resourceManager.getString(R.string.text_choose_wallet)
    }

    fun getEmptyLoaner(): String{
        return resourceManager.getString(R.string.text_loaner)
    }

    fun getTextLoan(): String{
        return resourceManager.getString(R.string.text_loan_title)
    }

    fun getTextBorrow(): String{
        return resourceManager.getString(R.string.text_borrow)
    }
}