package datn.datn_expansemanagement.screen.add_expense_donate.presentation

import android.graphics.drawable.Drawable
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.provider.AndroidResourceProvider

class AddExpenseDonateResource : AndroidResourceProvider(){
    fun getIconExpand(): Drawable?{
        return resourceManager.getDrawable(R.drawable.ic_keyboard_arrow_down_blue_24dp)
    }

    fun getIconCollapse(): Drawable?{
        return resourceManager.getDrawable(R.drawable.ic_keyboard_arrow_up_blue_24dp)
    }

    fun getTextExpand(): String{
        return resourceManager.getString(R.string.text_add_detail)
    }


    fun getTextCollapse(): String{
        return resourceManager.getString(R.string.text_collapse)
    }

    fun getColorTotalMoney(): Int{
        return resourceManager.getColor(R.color.color_ee403f)
    }

    fun getColorTotalMoneyReceive(): Int{
        return resourceManager.getColor(R.color.color_51c471)
    }

    fun getTextCategoryEmpty(): String{
        return resourceManager.getString(R.string.text_choose_category)
    }

    fun getTextWalletEmpty(): String{
        return resourceManager.getString(R.string.text_choose_wallet)
    }

    fun getTextChooseFriend(): String{
        return resourceManager.getString(R.string.text_hint_for_friend)
    }

    fun getColorCategory(): Int{
        return resourceManager.getColor(R.color.black)
    }

    fun getColorEmpty(): Int{
        return resourceManager.getColor(R.color.color_bdbec1)
    }
}