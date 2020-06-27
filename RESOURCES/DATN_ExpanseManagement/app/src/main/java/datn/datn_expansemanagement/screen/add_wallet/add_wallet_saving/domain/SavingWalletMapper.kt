package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain

import com.facebook.internal.Utility
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel

class SavingWalletMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddWalletHeaderItemViewModel())
        return list
    }
}
