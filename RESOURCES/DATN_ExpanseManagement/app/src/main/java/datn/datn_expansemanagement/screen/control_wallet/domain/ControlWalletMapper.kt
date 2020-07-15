package datn.datn_expansemanagement.screen.control_wallet.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.WalletViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletDesViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletHeaderViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletTitleViewModel
import datn.datn_expansemanagement.screen.control_wallet.presentation.model.ControlWalletToViewModel

class ControlWalletMapper(private val isOtherWallet: Boolean?) :
    Mapper<WalletViewModel, MutableList<ViewModel>> {
    override fun map(input: WalletViewModel): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()

        if(isOtherWallet == true){
            list.add(ControlWalletTitleViewModel(name = "Từ"))
            list.add(
                ControlWalletHeaderViewModel(
                    nameWallet = input.name.getValueOrDefaultIsEmpty(),
                    price = 0.0,
                    maxPrice = input.money.getValueOrDefaultIsZero(),
                    title = "Nhập số tiền cần chuyển",
                    isOtherWallet = true
                )
            )
            list.add(ControlWalletDesViewModel())
            list.add(ControlWalletTitleViewModel(name = "Đến"))
            list.add(ControlWalletToViewModel())
        }else{
            list.add(
                ControlWalletHeaderViewModel(
                    nameWallet = input.name.getValueOrDefaultIsEmpty(),
                    price = input.money.getValueOrDefaultIsZero(),
                    title = "Nhập số dư hiện tại của ví"
                )
            )
            list.add(ControlWalletDesViewModel(des = input.des))
        }
        return list
    }

}