package datn.datn_expansemanagement.screen.account.item_account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

class ItemAccountMapper(private val tapId: Int? = null) :
    Mapper<WalletResponse, MutableList<ViewModel>> {
    override fun map(input: WalletResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        val listItem = mutableListOf<ViewModel>()
        var totalPrice = 0.0
        if (tapId == 1) {
            if (!input.data.isNullOrEmpty()) {
                input.data.forEach {
                    listItem.add(
                        WalletViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            money = it.amountWallet.getValueOrDefaultIsZero(),
                            tabId = 1,
                            des = it.description.getValueOrDefaultIsEmpty()
                        )
                    )
                    totalPrice += it.amountWallet
                }
            }
            if(listItem.isNotEmpty()){
                val dataLast = listItem.last() as WalletViewModel
                dataLast.isLast = true
            }
        }
        else{
            listItem.add(ItemAccountAccumulationViewModel(
                id = 0,
                name = "Tài khoản tiết kiệm",
                moneyAccumulation = 3000000.0,
                moneyCurrent = 250000.0,
                isLast = true
            ))
//            totalPrice += amountWallet
        }
        listReturn.add(ItemAccountTotalMoneyViewModel(total = totalPrice))
        listReturn.addAll(listItem)
        return listReturn
    }

}