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
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import kotlinx.android.synthetic.main.item_wallet.view.*

class WalletViewRenderer(
    context: Context,
    private val onActionClickMore: OnActionData<WalletViewModel>,
    private val onItemClick : OnActionData<WalletViewModel>
) : ViewRenderer<WalletViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_wallet
    }

    override fun getModelClass(): Class<WalletViewModel> = WalletViewModel::class.java
    override fun bindView(model: WalletViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        val currentPrice = Utils.formatMoney(model.currentPrice)
        viewRoot.tvCurrentPrice.text = "Còn lại : ".plus(currentPrice)
        val money = Utils.formatMoney(model.money)
        viewRoot.tvMoney.text = money

        if (model.isLast) {
            viewRoot.viewBottom.gone()
        } else {
            viewRoot.viewBottom.visible()
        }

        viewRoot.imgMore.setOnClickListener {
            EventFireUtil.fireEvent(onActionClickMore, model)
        }

        viewRoot.clItem.setOnClickListener {
            EventFireUtil.fireEvent(onItemClick, model)
        }
    }


}