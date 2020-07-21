package datn.datn_expansemanagement.screen.report.presentation.renderer

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel
import kotlinx.android.synthetic.main.item_wallet.view.*

class GetWalletItemViewRenderer(context: Context) : ViewRenderer<GetWalletItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_wallet
    }

    override fun getModelClass(): Class<GetWalletItemViewModel> = GetWalletItemViewModel::class.java

    @SuppressLint("ResourceAsColor")
    override fun bindView(model: GetWalletItemViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        if (!model.isCreditCard) {
            if (model.currentMoney >= 0) {
                viewRoot.tvCurrentPrice.text =
                    "Còn lại : ".plus(Utils.formatMoney(model.currentMoney))
                viewRoot.tvCurrentPrice.setTextColor(context.resources.getColor(R.color.color_389b54))
            } else {
                viewRoot.tvCurrentPrice.setTextColor(context.resources.getColor(R.color.color_ee403f))
                viewRoot.tvCurrentPrice.text = "Nợ : ".plus(Utils.formatMoney(model.currentMoney))
            }
        }else{
            viewRoot.tvCurrentPrice.invisible()
        }

        val money = Utils.formatMoney(model.money)
        viewRoot.tvMoney.text = money
        if (model.isChoose) {
            viewRoot.imgMore.visible()
            GlideImageHelper(context).loadThumbnail(viewRoot.imgMore, "", R.drawable.ic_checked)
        } else {
            viewRoot.imgMore.gone()
        }
    }

}