package datn.datn_expansemanagement.screen.control_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.GetItemWalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletHeaderViewModel

class ControlWalletMapper : Mapper<GetItemWalletResponse, MutableList<ViewModel>>{
    override fun map(input: GetItemWalletResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(input != null){
            list.add(ControlWalletHeaderViewModel(
                nameWallet = input.nameWallet.getValueOrDefaultIsEmpty(),
                price = input.amountWallet.getValueOrDefaultIsZero()
            ))
        }
        return list
    }

}