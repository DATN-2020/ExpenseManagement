package datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.*

class DefaultWalletMapper(private val mResource: AddWalletResource) :
    Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddWalletHeaderItemViewModel())
        list.add(AddWalletNameItemViewModel())
        list.add(
            AddWalletTypeItemViewModel(
                type = AddWalletTypeItemViewModel.Type.DEFAULT,
                hint = mResource.getHintTypeWallet()
            )
        )
        list.add(AddWalletNoteItemViewModel())
        list.add(AddWalletBottomItemViewModel())
        return list
    }

}