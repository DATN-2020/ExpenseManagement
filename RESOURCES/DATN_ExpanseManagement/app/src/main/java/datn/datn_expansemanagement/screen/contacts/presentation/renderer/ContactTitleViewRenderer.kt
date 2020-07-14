package datn.datn_expansemanagement.screen.contacts.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.contacts.presentation.model.ContactTitleViewModel
import kotlinx.android.synthetic.main.item_layout_control_wallet_title.view.*

class ContactTitleViewRenderer(context: Context) : ViewRenderer<ContactTitleViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_control_wallet_title
    }

    override fun getModelClass(): Class<ContactTitleViewModel> = ContactTitleViewModel::class.java

    override fun bindView(model: ContactTitleViewModel, viewRoot: View) {
        viewRoot.tvTitle.text = model.name
    }

}