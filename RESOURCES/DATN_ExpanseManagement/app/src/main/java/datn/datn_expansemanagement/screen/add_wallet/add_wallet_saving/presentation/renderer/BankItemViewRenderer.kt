package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation.model.BankItemViewModel
import kotlinx.android.synthetic.main.item_layout_bank.view.*

class BankItemViewRenderer (context: Context): ViewRenderer<BankItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_bank
    }

    override fun getModelClass(): Class<BankItemViewModel> = BankItemViewModel::class.java

    override fun bindView(model: BankItemViewModel, viewRoot: View) {
        viewRoot.tvNameBank.text = model.name
        viewRoot.tvInterest.text = "Lãi suất ${model.interest} %/năm"
    }

}