package datn.datn_expansemanagement.screen.contacts.domain

import android.database.Cursor
import android.provider.ContactsContract
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.contacts.presentation.model.ContactsViewModel
import java.util.*

class ContractsMapper(private val mvpActivity: MvpActivity) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val phones: Cursor? = mvpActivity.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
            null, null
        )
        var idContact = 1
        val mobileNoSet = HashSet<String>()
        while (phones!!.moveToNext()) {
            val name = phones
                .getString(
                    phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                )
            val phoneNumber = phones
                .getString(
                    phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                )
            if (!mobileNoSet.contains(phoneNumber)) {
                val user = ContactsViewModel(id = idContact, name = name, nameChar = name.substring(0, 2))
                list.add(user)
                idContact++
            }
        }
        phones.close()
        list.add(ContactsViewModel(id = 0, name = "Bạn mới", isNew = true, isLast = true, nameChar = "BM"))
        return list
    }

}