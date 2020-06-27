package datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel

class AccumulationWalletMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddWalletHeaderItemViewModel())
        return list
    }
}