package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.BankResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation.model.BankItemViewModel

class GetListBankMapper : Mapper<List<BankResponse>, MutableList<ViewModel>>{
    override fun map(input: List<BankResponse>): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.isNullOrEmpty()){
            input.forEach {
                list.add(BankItemViewModel(
                    id = it.idBank.getValueOrDefaultIsZero(),
                    name = it.nameBank.getValueOrDefaultIsEmpty(),
                    interest = it.interest.getValueOrDefaultIsZero()
                ))
            }
        }
        return list
    }

}