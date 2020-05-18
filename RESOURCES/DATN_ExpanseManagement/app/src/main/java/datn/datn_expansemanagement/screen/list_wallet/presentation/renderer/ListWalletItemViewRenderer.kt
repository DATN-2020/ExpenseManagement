package datn.datn_expansemanagement.screen.list_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.list_wallet.presentation.model.ListWalletItemViewModel
import kotlinx.android.synthetic.main.item_layout_list_wallet.view.*

class ListWalletItemViewRenderer (context: Context): ViewRenderer<ListWalletItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_list_wallet
    }

    override fun getModelClass(): Class<ListWalletItemViewModel> = ListWalletItemViewModel::class.java

    override fun bindView(model: ListWalletItemViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        val money = Utils.formatMoneyVND(model.totalMoney)
        viewRoot.tvMoney.text = money
        if(model.isLast){
            viewRoot.viewBottom.gone()
        }else{
            viewRoot.viewBottom.visible()
        }

        if(model.isChoose){
            viewRoot.imgMore.visible()
        }else{
            viewRoot.imgMore.invisible()
        }
    }

}