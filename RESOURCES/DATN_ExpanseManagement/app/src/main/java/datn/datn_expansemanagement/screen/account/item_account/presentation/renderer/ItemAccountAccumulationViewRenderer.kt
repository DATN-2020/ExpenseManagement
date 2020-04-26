package datn.datn_expansemanagement.screen.account.item_account.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.*

class ItemAccountAccumulationViewRenderer (context: Context,
                                           private val onActionClickMore: OnActionData<ItemAccountAccumulationViewModel>): ViewRenderer<ItemAccountAccumulationViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_account_accumulation
    }

    override fun getModelClass(): Class<ItemAccountAccumulationViewModel>  = ItemAccountAccumulationViewModel::class.java

    override fun bindView(model: ItemAccountAccumulationViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        var money = Utils.formatMoneyVND(model.moneyAccum)
        viewRoot.tvAccumulation.text = money
        money =  Utils.formatMoneyVND(model.moneyCurrent)
        viewRoot.tvCurrent.text = money
        money =  Utils.formatMoneyVND(model.moneyRest)
        viewRoot.tvRest.text = money

        if(model.isLast){
            viewRoot.viewBottom.gone()
        }else{
            viewRoot.viewBottom.visible()
        }

        viewRoot.imgMore.setOnClickListener {
            EventFireUtil.fireEvent(onActionClickMore, model)
        }
    }

}