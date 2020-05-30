package datn.datn_expansemanagement.screen.contacts

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.contacts.presentation.ContactsView

class ContactsActivity : MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return ContactsView(this, ContactsView.ViewCreator(this, null))
    }
}