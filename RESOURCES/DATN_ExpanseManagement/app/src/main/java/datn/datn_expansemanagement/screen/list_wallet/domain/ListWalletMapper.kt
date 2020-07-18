package datn.datn_expansemanagement.screen.list_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.list_wallet.presentation.model.ListWalletItemViewModel

class ListWalletMapper(private val idWallet: Int? = null) :
    Mapper<WalletResponse, MutableList<ViewModel>> {
    override fun map(input: WalletResponse): MutableList<ViewModel> {
        val listItem = mutableListOf<ViewModel>()
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                if(it.idWallet == idWallet){
                    listItem.add(
                        ListWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            currentPrice = it.amountNow.getValueOrDefaultIsZero(),
                            totalMoney = it.amountWallet.getValueOrDefaultIsZero(),
                            isChoose = true
                        )
                    )
                }else{
                    listItem.add(
                        ListWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            currentPrice = it.amountNow.getValueOrDefaultIsZero(),
                            totalMoney = it.amountWallet.getValueOrDefaultIsZero()
                        )
                    )
                }

            }
        }

        (listItem.last() as ListWalletItemViewModel).isLast = true
        return listItem
    }

}