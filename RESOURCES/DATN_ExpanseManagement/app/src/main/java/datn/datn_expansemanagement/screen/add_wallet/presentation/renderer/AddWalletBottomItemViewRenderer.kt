package datn.datn_expansemanagement.screen.add_wallet.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletBottomItemViewModel
import kotlinx.android.synthetic.main.item_layout_add_wallet_bottom_vholder.view.*

class AddWalletBottomItemViewRenderer (context: Context,
private val onAction: OnActionNotify): ViewRenderer<AddWalletBottomItemViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_wallet_bottom_vholder
    }

    override fun getModelClass(): Class<AddWalletBottomItemViewModel> = AddWalletBottomItemViewModel::class.java

    override fun bindView(model: AddWalletBottomItemViewModel, viewRoot: View) {
        viewRoot.btnStart.setOnClickListener {
            EventFireUtil.fireEvent(onAction)
        }
    }

}