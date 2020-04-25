package datn.datn_expansemanagement.screen.account.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.account.presentation.model.WalletViewModel
import kotlinx.android.synthetic.main.item_wallet.view.*

class WalletViewRenderer (context: Context): ViewRenderer<WalletViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_wallet
    }

    override fun getModelClass(): Class<WalletViewModel> = WalletViewModel::class.java
    override fun bindView(model: WalletViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        viewRoot.tvMoney.text = model.money
    }


}