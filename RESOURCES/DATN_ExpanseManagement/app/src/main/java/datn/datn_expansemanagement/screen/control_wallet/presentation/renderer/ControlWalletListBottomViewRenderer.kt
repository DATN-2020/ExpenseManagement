package datn.datn_expansemanagement.screen.control_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletListBottomViewModel
import kotlinx.android.synthetic.main.item_wallet.view.*

class ControlWalletListBottomViewRenderer (context: Context): ViewRenderer<ControlWalletListBottomViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_wallet
    }

    override fun getModelClass(): Class<ControlWalletListBottomViewModel> = ControlWalletListBottomViewModel::class.java

    override fun bindView(model: ControlWalletListBottomViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        viewRoot.tvMoney.text = Utils.formatMoney(model.price)
        viewRoot.imgMore.gone()
    }

}