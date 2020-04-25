package datn.datn_expansemanagement.screen.account.item_account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountTotalMoneyViewModel
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel

class ItemAccountMapper : Mapper<Int, MutableList<ViewModel>> {
    override fun map(input: Int): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()

        listReturn.add(
            ItemAccountTotalMoneyViewModel(
                total = 500000.0
            )
        )

        when (input) {
            1 -> {
                for (i in 1..5) {
                    listReturn.add(
                        WalletViewModel(
                            id = i,
                            tabId = 1,
                            name = "Ví tiền mặt",
                            money = 1000000.0,
                            isLast = false
                        )
                    )
                }
                listReturn.add(
                    WalletViewModel(
                        id = 6,
                        tabId = 1,
                        name = "Ví tiền mặt",
                        money = 1000000.0,
                        isLast = true
                    )
                )
            }
            2 -> {
                for (i in 1..5) {
                    listReturn.add(
                        WalletViewModel(
                            id = i,
                            tabId = 2,
                            name = "Ví tiền mặt",
                            money = 1000000.0,
                            isLast = false
                        )
                    )
                }
                listReturn.add(
                    WalletViewModel(
                        id = 6,
                        tabId = 2,
                        name = "Ví tiền mặt",
                        money = 1000000.0,
                        isLast = true
                    )
                )
            }
            3 -> {
                for (i in 1..5) {
                    listReturn.add(
                        ItemAccountAccumulationViewModel(
                            id = i,
                            name = "Ví tiền mặt",
                            moneyAccum = 1000000.0,
                            moneyCurrent = 700000.0,
                            moneyRest =  300000.0,
                            isLast = false
                        )
                    )
                }
                listReturn.add(
                    ItemAccountAccumulationViewModel(
                        id = 6,
                        moneyAccum = 1000000.0,
                        moneyCurrent = 700000.0,
                        moneyRest =  300000.0,
                        name = "Ví tiền mặt",
                        isLast = true
                    )
                )
            }
        }
        return listReturn
    }

}