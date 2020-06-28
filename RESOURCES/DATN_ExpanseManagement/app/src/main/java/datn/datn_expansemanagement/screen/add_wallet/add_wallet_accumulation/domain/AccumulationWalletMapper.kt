package datn.datn_expansemanagement.screen.add_wallet.add_wallet_accumulation.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletBottomItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletHeaderItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletNameItemViewModel
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletTypeItemViewModel

class AccumulationWalletMapper(private val mResource: AddWalletResource) :
    Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddWalletHeaderItemViewModel())
        list.add(AddWalletNameItemViewModel(type = AddWalletNameItemViewModel.Type.PAY_TO))
        list.add(
            AddWalletTypeItemViewModel(
                type = AddWalletTypeItemViewModel.Type.START_DATE,
                hint = mResource.getTextStartDate()
            )
        )
        list.add(
            AddWalletTypeItemViewModel(
                type = AddWalletTypeItemViewModel.Type.START_DATE,
                hint = mResource.getTextPayTime()
            )
        )
        list.add(AddWalletBottomItemViewModel())
        return list
    }
}