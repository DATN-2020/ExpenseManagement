package datn.datn_expansemanagement.screen.report.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel

class GetWalletMapper(private val idWallet: Int? = null) : Mapper<WalletResponse, MutableList<ViewModel>> {
    override fun map(input: WalletResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()

        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                if(it.idWallet == idWallet){
                    listReturn.add(
                        GetWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            money = it.amountWallet.getValueOrDefaultIsZero(),
                            currentMoney = it.amountNow.getValueOrDefaultIsZero(),
                            isChoose = true
                        )
                    )
                }else{
                    listReturn.add(
                        GetWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            money = it.amountWallet.getValueOrDefaultIsZero(),
                            currentMoney = it.amountNow.getValueOrDefaultIsZero()
                        )
                    )
                }
            }
        }

        return listReturn
    }

}