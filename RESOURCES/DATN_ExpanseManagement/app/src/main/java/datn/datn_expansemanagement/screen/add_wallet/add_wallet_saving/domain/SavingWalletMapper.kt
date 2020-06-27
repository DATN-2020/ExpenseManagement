package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletBottomItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNameItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel
import datn.datn_expansemanagement.screen.overview.presentation.model.EmptyLineViewModel

class SavingWalletMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddWalletHeaderItemViewModel())
        list.add(AddWalletNameItemViewModel())
        list.add(AddWalletTypeItemViewModel(type = AddWalletTypeItemViewModel.Type.SAVING))
        list.add(EmptyLineViewModel())
        list.add(AddWalletTypeItemViewModel(type = AddWalletTypeItemViewModel.Type.START_DATE))
        list.add(AddWalletTypeItemViewModel(type = AddWalletTypeItemViewModel.Type.PERIOD))
        list.add(AddWalletBottomItemViewModel())

        return list
    }
}
