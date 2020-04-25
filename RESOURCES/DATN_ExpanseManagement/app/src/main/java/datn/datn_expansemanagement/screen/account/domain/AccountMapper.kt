package datn.datn_expansemanagement.screen.account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.account.presentation.model.WalletViewModel

class AccountMapper: Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        for (i in 1..3) {
            listReturn.add(
                WalletViewModel(
                    id = i,
                    imageurl = "",
                    name = "Ví tiền mặt",
                    money = "1.000.000$"
                )
            )
        }
        return listReturn
    }

}