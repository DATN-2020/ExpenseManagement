package datn.datn_expansemanagement.screen.control_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletDesViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletHeaderViewModel

class ControlWalletMapper(isOtherWallet: Boolean?) :
    Mapper<WalletViewModel, MutableList<ViewModel>> {
    override fun map(input: WalletViewModel): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(
            ControlWalletHeaderViewModel(
                nameWallet = input.name.getValueOrDefaultIsEmpty(),
                price = input.money.getValueOrDefaultIsZero()
            )
        )

        list.add(ControlWalletDesViewModel(des = input.des))
        return list
    }

}