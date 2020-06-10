package datn.datn_expansemanagement.screen.contacts.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.contacts.presentation.model.ContactsViewModel
import kotlinx.android.synthetic.main.item_layout_trip.view.*

class ContactsItemViewRenderer (context: Context): ViewRenderer<ContactsViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_trip
    }

    override fun getModelClass(): Class<ContactsViewModel> = ContactsViewModel::class.java

    override fun bindView(model: ContactsViewModel, viewRoot: View) {
        viewRoot.imgEdit.gone()
        viewRoot.tvTrip.text = model.name
        viewRoot.tvNameImg.text = model.nameChar
        if(model.isLast){
            viewRoot.viewBottom.invisible()
        }else{
            viewRoot.viewBottom.visible()
        }
    }

}