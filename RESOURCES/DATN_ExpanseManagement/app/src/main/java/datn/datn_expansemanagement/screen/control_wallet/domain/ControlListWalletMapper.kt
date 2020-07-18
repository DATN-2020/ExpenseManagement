package datn.datn_expansemanagement.screen.control_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletListBottomViewModel


class ControlListWalletMapper(private val idWallet: Int) : Mapper<WalletResponse, MutableList<ViewModel>>{
    override fun map(input: WalletResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.data.isNullOrEmpty()){
            input.data.forEach {
                if(it.idWallet != idWallet){
                    list.add(ControlWalletListBottomViewModel(
                        id = it.idWallet.getValueOrDefaultIsZero(),
                        name = it.nameWallet.getValueOrDefaultIsEmpty(),
                        price = it.amountWallet.getValueOrDefaultIsZero(),
                        currentPrice = it.amountNow.getValueOrDefaultIsZero()
                    ))
                }
            }
        }
        return list
    }

}