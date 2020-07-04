package datn.datn_expansemanagement.screen.account.item_account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

class ItemAccountMapper(private val tapId: Int? = null) :
    Mapper<List<WalletResponse>, MutableList<ViewModel>> {
    override fun map(input: List<WalletResponse>): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val listItem = mutableListOf<ViewModel>()
        var totalPrice = 0.0
        if (tapId == 1) {
            if (!input.isNullOrEmpty()) {
                input.forEach {
                    listItem.add(
                        WalletViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            money = it.amountWallet.getValueOrDefaultIsZero().toDouble(),
                            tabId = 1
                        )
                    )
                    totalPrice += it.amountWallet.toDouble()
                }
            }
            val dataLast = listItem.last() as WalletViewModel
            dataLast.isLast = true
        }
        listReturn.add(ItemAccountTotalMoneyViewModel(total = totalPrice))
        listReturn.addAll(listItem)
        return listReturn
    }

}