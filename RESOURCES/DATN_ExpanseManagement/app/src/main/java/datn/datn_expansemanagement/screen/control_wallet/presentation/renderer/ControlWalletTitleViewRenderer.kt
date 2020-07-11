package datn.datn_expansemanagement.screen.control_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletTitleViewModel
import kotlinx.android.synthetic.main.item_layout_control_wallet_title.view.*

class ControlWalletTitleViewRenderer (context: Context): ViewRenderer<ControlWalletTitleViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_control_wallet_title
    }

    override fun getModelClass(): Class<ControlWalletTitleViewModel> = ControlWalletTitleViewModel::class.java

    override fun bindView(model: ControlWalletTitleViewModel, viewRoot: View) {
        viewRoot.tvTitle.text = model.name
    }

}