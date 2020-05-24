package datn.datn_expansemanagement.screen.list_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.list_wallet.presentation.model.ListWalletItemViewModel

class ListWalletMapper(private val idWallet: Int? = null) : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(ListWalletItemViewModel(id = 1, name = "Ví tiền mặt", totalMoney = 5000000.0))
        list.add(ListWalletItemViewModel(id = 2, name = "Lương", totalMoney = 4500000.0,isLast = true))
        list.forEach {
            if(it is ListWalletItemViewModel){
                if(it.id == idWallet){
                    it.isChoose = true
                }
            }
        }
        return list
    }

}