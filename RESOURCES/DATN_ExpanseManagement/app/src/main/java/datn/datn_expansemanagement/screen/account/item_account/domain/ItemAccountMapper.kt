package datn.datn_expansemanagement.screen.account.item_account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

class ItemAccountMapper :
    Mapper<WalletResponse, MutableList<ViewModel>> {
    override fun map(input: WalletResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val listItem = mutableListOf<ViewModel>()
        var totalPrice = 0.0
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                listItem.add(
                    WalletViewModel(
                        id = it.idWallet.getValueOrDefaultIsZero(),
                        name = it.nameWallet.getValueOrDefaultIsEmpty(),
                        money = it.amountWallet.getValueOrDefaultIsZero(),
                        des = it.description.getValueOrDefaultIsEmpty(),
                        currentPrice = it.amountNow.getValueOrDefaultIsZero()
                    )
                )
                totalPrice += it.amountWallet
            }
        }
        if (listItem.isNotEmpty()) {
            val dataLast = listItem.last() as WalletViewModel
            dataLast.isLast = true
        }
        listReturn.add(ItemAccountTotalMoneyViewModel(total = totalPrice))
        listReturn.addAll(listItem)
        return listReturn
    }

}