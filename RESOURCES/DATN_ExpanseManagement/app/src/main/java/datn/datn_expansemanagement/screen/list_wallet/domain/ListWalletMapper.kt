package datn.datn_expansemanagement.screen.list_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.list_wallet.presentation.model.ListWalletItemViewModel

class ListWalletMapper(private val idWallet: Int? = null) :
    Mapper<List<WalletResponse>, MutableList<ViewModel>> {
    override fun map(input: List<WalletResponse>): MutableList<ViewModel> {
        val listItem = mutableListOf<ViewModel>()
        if (!input.isNullOrEmpty()) {
            input.forEach {
                if (it.idWallet == idWallet) {
                    listItem.add(ListWalletItemViewModel(
                        id = it.idWallet.getValueOrDefaultIsZero(),
                        name = it.nameWallet.getValueOrDefaultIsEmpty(),
                        totalMoney = it.amountWallet.getValueOrDefaultIsZero().toDouble(),
                        isChoose = true
                    ))
                } else {
                    listItem.add(
                        ListWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            totalMoney = it.amountWallet.getValueOrDefaultIsZero().toDouble()
                        )
                    )
                }
            }
        }
        val dataLast = listItem.last() as ListWalletItemViewModel
        dataLast.isLast = true
        return listItem
    }

}