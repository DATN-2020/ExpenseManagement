package datn.datn_expansemanagement.screen.contacts.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ContactsViewModel(
    var id: Int,
    var name: String,
    var isNew: Boolean = false
) : ViewModel