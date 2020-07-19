package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletRateItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_rate_vholder.view.*

class AddWalletRateItemViewRenderer(context: Context) :
    ViewRenderer<AddWalletRateItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_rate_vholder
    }

    override fun getModelClass(): Class<AddWalletRateItemViewModel> =
        AddWalletRateItemViewModel::class.java

    override fun bindView(model: AddWalletRateItemViewModel, viewRoot: View) {
        viewRoot.tvTitleChooseDate.text = model.title
        if (model.isResult) {
            viewRoot.tvUnit.gone()
            viewRoot.edtRate.text = (model.rate.getValueOrDefaultIsZero() * model.price).toString()
        } else {
            viewRoot.tvUnit.text = model.unit
            viewRoot.edtRate.text = model.rate.getValueOrDefaultIsZero().toString()
        }

    }

}