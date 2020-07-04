package datn.datn_expansemanagement.screen.add_wallet.presentation.mapper

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TypeWalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.TypeWalletItemViewModel

class TypeWalletMapper : Mapper<List<TypeWalletResponse>, MutableList<ViewModel>>{
    override fun map(input: List<TypeWalletResponse>): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.isNullOrEmpty()){
            input.forEach {
                list.add(TypeWalletItemViewModel(
                    id = it.idTypeWallet.getValueOrDefaultIsZero(),
                    name = it.nameTypeWallet.getValueOrDefaultIsEmpty(),
                    img = it.imageTypeWallet.getValueOrDefaultIsEmpty()
                ))
            }
        }
        return list
    }
}