package datn.datn_expansemanagement.screen.list_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.list_wallet.presentation.model.ListWalletItemViewModel

class ListWalletMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(ListWalletItemViewModel(id = 1, name = "Ví tiền mặt", totalMoney = 5000000.0, isChoose = true))
        list.add(ListWalletItemViewModel(id = 2, name = "Lương", totalMoney = 4500000.0,isLast = true))
        return list
    }

}